.PHONY all:
.PHONY clean:
.PHONY clobber:

all:
	@./compile.sh

clean:
	@./compile.sh clean

clobber: clean
