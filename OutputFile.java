//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class OutputFile {
    private PrintWriter stdout;
    private StringTokenizer st;
    private String currentWord;
    private String nextWord;
    private boolean EOF;
    private String filename;
    private String line;

    public OutputFile() {
    }

    public void close() {
        try {
            this.stdout.close();
        } catch (Exception var1) {
        }

    }

    private void error(String var1) {
        System.err.println("> I/O ERROR writting " + var1 + " in file " + this.filename);
    }

    public boolean open(String var1) {
        this.nextWord = null;
        this.filename = var1;

        try {
            this.stdout = new PrintWriter(new BufferedWriter(new FileWriter(var1)));
            return true;
        } catch (Exception var2) {
            System.out.println("> I/O ERROR opening file " + var1);
            return false;
        }
    }

    public void writeChar(char var1) {
        try {
            this.stdout.print(var1 + " ");
        } catch (Exception var2) {
            this.error("char " + var1);
        }

    }

    public void writeDouble(double var1) {
        try {
            this.stdout.print(var1);
        } catch (Exception var3) {
            this.error("double " + var1);
        }

    }

    public void writeInt(int var1) {
        try {
            this.stdout.print(var1 + " ");
        } catch (Exception var2) {
            this.error("int " + var1);
        }

    }

    public void writeString(String var1) {
        try {
            this.stdout.print(var1);
        } catch (Exception var2) {
            this.error("String " + var1);
        }

    }

    public void writeln() {
        try {
            this.stdout.println();
        } catch (Exception var1) {
            this.error("[CR]");
        }

    }
}
