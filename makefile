# Makefile for ProducerConsumer Java program

# Java compiler
JAVAC = javac

# Java source files
SOURCES = ProducerConsumer.java

# Output directory
OUTDIR = bin

# Main class
MAIN_CLASS = ProducerConsumer

# Java compiler flags
JAVACFLAGS = -d $(OUTDIR)

# Targets
all: $(OUTDIR)/$(MAIN_CLASS).class

run: all
	java $(JVMFLAGS) -cp $(OUTDIR) $(MAIN_CLASS)

$(OUTDIR)/$(MAIN_CLASS).class: $(SOURCES)
	$(JAVAC) $(JAVACFLAGS) $(CLASSPATH) $^

clean:
	rm -rf $(OUTDIR)/*.class

.PHONY: all run clean