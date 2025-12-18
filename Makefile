JAVAC = javac
JAVA = java
SOURCES = Executable.java GraphC.java Sommet.java
CLASSES = Executable.class GraphC.class Sommet.class

all: compile run

compile:
	$(JAVAC) $(SOURCES)

run: $(CLASSES)
	$(JAVA) Executable


clean:
	rm -f $(CLASSES)

rebuild: clean compile run
