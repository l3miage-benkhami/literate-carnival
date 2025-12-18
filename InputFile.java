//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class InputFile {
    private BufferedReader stdin;
    private StringTokenizer st;
    private String currentWord;
    private String nextWord;
    private boolean EOF;
    private String filename;
    private String line;

    public InputFile() {
    }

    public void close() {
        try {
            this.stdin.close();
            this.EOF = true;
        } catch (Exception var1) {
            System.out.println("> I/O ERROR : Unable to close file " + this.filename);
        }

    }

    public boolean eof() {
        return this.nextWord == null;
    }

    private void error(String var1) {
        System.err.println("> I/O ERROR reading " + var1 + " in file " + this.filename);
    }

    public boolean open(String var1) {
        this.nextWord = null;
        this.filename = var1;

        try {
            this.stdin = new BufferedReader(new FileReader(var1));
            this.EOF = false;
        } catch (Exception var2) {
            System.out.println("> I/O ERROR : Unable to open file " + var1);
            this.EOF = true;
        }

        this.readNextWord();
        return this.EOF ^ true;
    }

    public double readDouble() {
        this.readNextWord();

        try {
            return Double.parseDouble(this.currentWord);
        } catch (Exception var1) {
            this.error("Double");
            return (double)0.0F;
        }
    }

    public int readInt() {
        this.readNextWord();

        try {
            return Integer.parseInt(this.currentWord);
        } catch (Exception var1) {
            this.error("Integer");
            return 0;
        }
    }

    private void readLine() {
        try {
            this.line = this.stdin.readLine();
            if (this.line == null) {
                this.EOF = true;
            } else {
                this.st = new StringTokenizer(this.line);
            }
        } catch (Exception var1) {
            this.st = null;
        }

    }

    public void readNextWord() {
        this.currentWord = this.nextWord;
        this.nextWord = null;

        while(!this.EOF && this.nextWord == null) {
            while(!this.EOF && (this.st == null || !this.st.hasMoreTokens())) {
                this.readLine();
            }

            if (this.st != null && this.st.hasMoreTokens()) {
                try {
                    this.nextWord = this.st.nextToken();
                } catch (Exception var1) {
                    this.nextWord = null;
                }
            }
        }

    }

    public String readString() {
        this.readNextWord();
        if (this.currentWord == null) {
            this.error("String");
        }

        return this.currentWord;
    }
}
