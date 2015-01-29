#!/bin/bash
# A very basic Cubition compile script for Mac + Linux
# This expects Maven is available in the environment under 'mvn'.

# Clean up environment
echo Cleaning up...
mvn clean

# Create directories for Javadocs
echo Building directory tree...
mkdir -p api/target/site/apidocs
mkdir -p client/target/site/apidocs
mkdir -p server/target/site/apidocs
mkdir -p bootstrap/target/site/apidocs
mkdir -p core/target/site/apidocs
mkdir -p out/javadoc-api
mkdir -p out/javadoc-client
mkdir -p out/javadoc-server
mkdir -p out/javadoc-bootstrap
mkdir -p out/javadoc-core

# Produce final product
echo Running Maven...
mvn package javadoc:javadoc
