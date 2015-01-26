Cubition
========

Cubition is a voxel-based game engine, inspired by Minecraft, that aims to
to ease the previously painful task of modding, network issues, and so on.
Any modifications made should be able to be easily implemented without having to look
at obscure code or other similar nasties.

While many similar projects exist, most don't make it off the ground, more
then often not due to lack of support or time on the developers behalf. We aim 
to fix all this, with a skilled team and a firm destination in mind.

Building
--------

We use [Apache Maven](http://maven.apache.org) to compile the project, as it has a simple
configuration format, and is cross-platform.

To compile:
- Install Maven / a Oracle JDK (if required)
- Clone, or [Download the .zip](https://github.com/Cubition/Cubition/archive/master.zip) of this project's source code
- Run `/path/to/mvn` in the source root
- The packaged results will be in the `out/` directory

To generate Javadocs (must be done independently of compile on Linux + Mac):
- Run `/path/to/mvn clean` to clean up the environment.
- Create required directories (via `mkdir`):
- `mkdir -p api/target/site/apidocs`
- `mkdir -p client/target/site/apidocs`
- `mkdir -p server/target/site/apidocs`
- `mkdir -p bootstrap/target/site/apidocs`
- `mkdir -p out/javadoc-api`
- `mkdir -p out/javadoc-client`
- `mkdir -p out/javadoc-server`
- `mkdir -p out/javadoc-bootstrap`
- Run `/path/to/mvn javadoc:javadoc package` to build the src + Javadocs.
- Your resulting documentation will be in `out/javadoc-*`

Contributions
-------------

We welcome contributions to the project, via pull requests.