JAVAC = javac
JAVA = java
SOURCES = HasMenu.java User.java CheckingAccount.java SavingsAccount.java Customer.java Admin.java CustomerList.java Bank.java

.PHONY: all run clean

all:
	$(JAVAC) $(SOURCES)

run: all
	$(JAVA) Bank

clean:
	rm -f *.class customers.dat
