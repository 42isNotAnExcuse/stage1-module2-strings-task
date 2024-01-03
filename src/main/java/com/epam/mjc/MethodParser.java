package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

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
        MethodSignature result;
        List<MethodSignature.Argument> argList = new ArrayList<>();
        String rawArgsStr;
        String argsStr;
        String methName;
        String[] rawSign;
        String[] parts = signatureString.split("\\(");

        rawArgsStr = parts[1].trim();
        argsStr = rawArgsStr.substring(0, rawArgsStr.length() - 1);

        if (!argsStr.isEmpty()) {
            for (String argPair : argsStr.split(",")) {
                String type = argPair.trim().split(" ")[0];
                String name = argPair.trim().split(" ")[1];
                argList.add(new MethodSignature.Argument(type, name));
            }
        }

        rawSign = parts[0].split(" ");
        methName = rawSign[rawSign.length - 1];
        result = argsStr.isEmpty() ? new MethodSignature(methName) : new MethodSignature(methName, argList);
        result.setReturnType(rawSign[rawSign.length - 2]);
        if (rawSign.length > 2)
            result.setAccessModifier(rawSign[0]);

        return result;
    }
}
