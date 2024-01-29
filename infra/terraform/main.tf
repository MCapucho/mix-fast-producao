resource "aws_ecs_task_definition" "mixfastproducao_ecs_task_definition" {
  family                   = "family_${var.name}"
  requires_compatibilities = ["FARGATE"]
  network_mode             = var.network_mode
  cpu                      = var.cpu
  memory                   = var.memory
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_role.arn
  tags                     = var.tags

  runtime_platform {
    operating_system_family = "LINUX"
    cpu_architecture        = "X86_64"
  }

  container_definitions    = <<DEFINITION
  [
    {
      "name": "container_${var.name}",
      "image": "022874923015.dkr.ecr.us-east-1.amazonaws.com/mixfastproducao:latest",
      "essential": true,
      "portMappings": [
        {
          "containerPort": ${var.port},
          "hostPort": ${var.port}
        }
      ],
      "memory": ${var.memory},
      "cpu": ${var.cpu},
      "networkMode": "awsvpc",
      "logConfiguration": {
        "logDriver": "awslogs",
        "options": {
          "awslogs-group": "/aws/ecs/${var.name}_ecs_cluster/cloudwatch/${var.name}",
          "awslogs-region": "us-east-1",
          "awslogs-stream-prefix": "${var.name}",
          "awslogs-create-group": "true"
        }
      },
      "environment": [
        {
          "name": "URL_GATEWAY",
          "value": "https://wifyye62fe.execute-api.us-east-1.amazonaws.com/mixfast"
        },
        {
          "name": "URL_GATEWAY_TOKEN",
          "value": "https://xzgdnfubcj.execute-api.us-east-1.amazonaws.com/mixfast-token"
        },
        {
          "name": "CLIENT_ID",
          "value": "10970049021"
        }
      ],
      "secrets": [

      ]
    }
  ]
  DEFINITION
}

resource "aws_ecs_service" "mixfastproducao_ecs_service" {
  name                 = "service_${var.name}"
  cluster              = var.ecs_cluster_name
  task_definition      = aws_ecs_task_definition.mixfastproducao_ecs_task_definition.arn
  desired_count        = 1
  force_new_deployment = true
  launch_type          = "FARGATE"

  network_configuration {
    security_groups  = [aws_security_group.mixfastproducao_security_group.id]
    subnets          = var.subnet_ids
    assign_public_ip = true
  }

  depends_on = [
    aws_ecs_task_definition.mixfastproducao_ecs_task_definition
  ]

  health_check_grace_period_seconds = 300

  load_balancer {
    target_group_arn = var.target_group_arn
    container_name   = "container_${var.name}"
    container_port   = var.port
  }

  lifecycle {
    ignore_changes = [
      desired_count
    ]
  }

  tags = var.tags
}