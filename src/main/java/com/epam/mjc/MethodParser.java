package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        final String NAME_REG = "(\\w+[(].*[)])";
        final String SPACE = " ";
        final String DELIMITER = ", ";
        final int ARGS_NUM = 3;

        List<MethodSignature.Argument> args = new ArrayList<>();

        StringTokenizer st1 = new StringTokenizer(signatureString, NAME_REG);
        ArrayList<String> parts = new ArrayList<>();
        while (st1.hasMoreTokens()) {
            parts.add(st1.nextToken());
        }
        if (parts.size() > 1) {
            final int IND_TYPE = 0;
            final int IND_NAME = 1;
            final int IND_ARGS = 1;

            args = Arrays.stream(parts.get(IND_ARGS).split(DELIMITER))
                    .map(e -> {
                        String[] part = e.split(SPACE);
                        return new MethodSignature.Argument(part[IND_TYPE], part[IND_NAME]);
                    })
                    .collect(Collectors.toList());
        }
        String[] nameSplit = parts.get(0).split(SPACE);

        MethodSignature result = new MethodSignature("", args);
        if (nameSplit.length == ARGS_NUM) {
            result.setAccessModifier(nameSplit[0]);
            result.setReturnType(nameSplit[1]);
            result.setMethodName(nameSplit[2]);
        } else {
            result.setReturnType(nameSplit[0]);
            result.setMethodName(nameSplit[1]);
        }
        return result;
    }
}
