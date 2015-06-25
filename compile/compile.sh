#!/bin/bash
# A very basic Cubition compile script for Mac + Linux
# This expects Maven is available in the environment under 'mvn'.

# export JAVA_HOME on Mac OS X
if [ `uname -s` == Darwin ]; then
    export JAVA_HOME=`/usr/libexec/java_home`
fi

# Check for maven
export MVN=`which mvn`

if [[ -z ${MVN} ]]; then
    printf 'Error: Maven is not installed in the environment.\n'
    printf '    Expected Maven to be installed as `mvn`\n'
    printf '    You can install this program from https://maven.apache.org/\n'
    exit 1
fi

# Clean up environment (if needed)
if [[ -e build ]]; then
    echo "Cleaning up from last build..."
    mvn clean > /dev/null 2>&1
    rm -rf build
    rm -rf */target
    rm -rf */dependency-reduced-pom.xml
fi

# Make sure user didn't just request clean
if [[ ${1} != clean ]]; then
    # Build all projects
    echo "Building Cubition..."
    env mvn package javadoc:javadoc

    # Symlink javadocs into build
    function linkdir {
        ln -s ../${1}/target/site/apidocs build/javadoc-${1}
    }

    linkdir api
    linkdir bootstrap
    linkdir server
    linkdir client
fi
