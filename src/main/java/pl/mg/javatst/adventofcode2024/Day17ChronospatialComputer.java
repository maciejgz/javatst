package pl.mg.javatst.adventofcode2024;

import java.util.ArrayList;
import java.util.List;

public class Day17ChronospatialComputer {


    public static void main(String[] args) {
        // Initialize registers
        int registerA = 44374556;
        int registerB = 0;
        int registerC = 0;

        // Program instructions
        int[] program = {2, 4, 1, 5, 7, 5, 1, 6, 0, 3, 4, 1, 5, 5, 3, 0};

        // Output collection
        List<Integer> output = new ArrayList<>();

        // Instruction pointer
        int instructionPointer = 0;

        while (instructionPointer < program.length) {
            int opcode = program[instructionPointer];
            int operand = program[instructionPointer + 1];

            switch (opcode) {
                case 0: // adv
                    registerA /= (int) Math.pow(2, resolveComboOperand(operand, registerA, registerB, registerC));
                    break;
                case 1: // bxl
                    registerB ^= operand;
                    break;
                case 2: // bst
                    registerB = resolveComboOperand(operand, registerA, registerB, registerC) % 8;
                    break;
                case 3: // jnz
                    if (registerA != 0) {
                        instructionPointer = operand;
                        continue;
                    }
                    break;
                case 4: // bxc
                    registerB ^= registerC;
                    break;
                case 5: // out
                    output.add(resolveComboOperand(operand, registerA, registerB, registerC) % 8);
                    break;
                case 6: // bdv
                    registerB = registerA / (int) Math.pow(2, resolveComboOperand(operand, registerA, registerB, registerC));
                    break;
                case 7: // cdv
                    registerC = registerA / (int) Math.pow(2, resolveComboOperand(operand, registerA, registerB, registerC));
                    break;
                default:
                    throw new IllegalStateException("Invalid opcode: " + opcode);
            }

            // Increment the instruction pointer
            instructionPointer += 2;
        }

        // Print the output as a comma-separated string
        System.out.println(String.join(",", output.stream().map(String::valueOf).toArray(String[]::new)));
    }

    // Resolve the value of a combo operand
    private static int resolveComboOperand(int operand, int registerA, int registerB, int registerC) {
        return switch (operand) {
            case 0, 1, 2, 3 -> operand;
            case 4 -> registerA;
            case 5 -> registerB;
            case 6 -> registerC;
            case 7 -> throw new IllegalStateException("Invalid combo operand: 7");
            default -> throw new IllegalStateException("Invalid combo operand: " + operand);
        };
    }


}
