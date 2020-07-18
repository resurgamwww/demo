package com.wht.demo.algorithm.util;

import org.springframework.lang.NonNull;

import java.util.List;
import java.util.regex.Matcher;

/**
 * Markdown工具类
 *
 * @author wht
 */
public class MarkdownUtil {

    public static void main(String[] args) {
        String a = "<no option>\n" +
                "When no option is used jmap prints shared object mappings. For each shared object loaded in the target VM, start address, the size of the mapping, and the full path of the shared object file are printed. This is similar to the Solaris pmap utility.\n" +
                "-dump:[live,]format=b,file=<filename>\n" +
                "Dumps the Java heap in hprof binary format to filename. The live suboption is optional. If specified, only the live objects in the heap are dumped. To browse the heap dump, you can use jhat (Java Heap Analysis Tool) to read the generated file.\n" +
                "-finalizerinfo\n" +
                "Prints information on objects awaiting finalization.\n" +
                "-heap\n" +
                "Prints a heap summary. GC algorithm used, heap configuration and generation wise heap usage are printed.\n" +
                "-histo[:live]\n" +
                "Prints a histogram of the heap. For each Java class, number of objects, memory size in bytes, and fully qualified class names are printed. VM internal class names are printed with '*' prefix. If the live suboption is specified, only live objects are counted.\n" +
                "-permstat\n" +
                "Prints class loader wise statistics of permanent generation of Java heap. For each class loader, its name, liveness, address, parent class loader, and the number and size of classes it has loaded are printed. In addition, the number and size of interned Strings are printed.\n" +
                "-F\n" +
                "Force. Use with jmap -dump or jmap -histo option if the pid does not respond. The live suboption is not supported in this mode.\n" +
                "-h\n" +
                "Prints a help message.\n" +
                "-help\n" +
                "Prints a help message.\n" +
                "-J<flag>\n" +
                "Passes <flag> to the Java virtual machine on which jmap is run.";

        //System.out.println(toTable(a,true));
        System.out.println(toTableFromArray(a.split("\n"), 2));
    }

    public static String toTableFromArray(@NonNull String[] data, int columnNum) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0, dataLength = data.length; i < dataLength; i += columnNum) {


            for (int j = 0; j < columnNum; j++) {
                String s = data[i + j];
                sb.append("|");
                sb.append(s);
            }
            sb.append("|\n");

        }

        return sb.toString();

    }

    public static String toTable(@NonNull String content, boolean hasTitle) {

        String[] split = content.split("\n");

        StringBuilder sb = new StringBuilder();

        if (hasTitle) {
            sb.append(toRow(split[0]));
            int i = split[0].split("\t").length;
            for (int j = 0; j < i; j++) {
                sb.append("|---");
            }
            sb.append("|\n");
        }

        for (int i = (hasTitle ? 1 : 0), splitLength = split.length; i < splitLength; i++) {
            String s = split[i];

            sb.append(toRow(s));

        }

        return sb.toString();
    }

    public static String toRow(@NonNull String s) {
        StringBuilder sb = new StringBuilder();
        String[] s1 = s.split("\t");


        for (String s2 : s1) {
            sb.append("|");
            sb.append(s2);
        }
        sb.append("|\n");

        return sb.toString();
    }


}
