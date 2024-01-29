terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.17.0"
    }
  }

  backend "s3" {
    bucket = "mixfastproducao-s3-bucket-app"
    key    = "mixfast-infrastructure"
    region = "us-east-1"
  }
}