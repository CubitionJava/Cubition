.PHONY all:
.PHONY clean:
.PHONY clobber:
.PHONY newapi:

NEWAPIDIR := newapi/src/main/java

all:
	@compile/compile.sh

clean:
	@compile/compile.sh clean
	@rm -rf newapi

clobber: clean

newapi: jsd/newapi.jsd jsd/generator.php
	@mkdir -p ${NEWAPIDIR}
	@php -f jsd/generator.php "$<" ${NEWAPIDIR}
