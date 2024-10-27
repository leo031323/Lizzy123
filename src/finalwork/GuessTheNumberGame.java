package finalwork;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GuessTheNumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("欢迎来到猜数字游戏！");
        while (playAgain) {
            startGame(scanner);
            playAgain = askPlayAgain(scanner);
        }
        System.out.println("感谢您的游玩，再见！\n2022307010235 夏启贤");
        scanner.close();
    }


    /*
    游戏主循环：每次玩家猜测后，根据大小提示用户，直到猜中或次数超限。
    计时功能：用 System.currentTimeMillis() 记录游戏时长。
    历史记录：将所有猜测存入 guessHistory 列表，以供后续查看。
    */
    private static void startGame(Scanner scanner) {
        int attempts = 0;
        List<Integer> guessHistory = new ArrayList<>();

        System.out.println("\n请选择难度等级:");
        System.out.println("1. 简单 (1-50)");
        System.out.println("2. 中等 (1-100)");
        System.out.println("3. 困难 (1-200)");
        System.out.println("4. 自定义难度");

        int difficultyLevel = getDifficultyLevel(scanner);
        int numberToGuess = generateRandomNumber(difficultyLevel);

        long startTime = System.currentTimeMillis();

        while (true) {
            System.out.print("请输入您的猜测: ");
            int guess = getValidatedGuess(scanner);
            attempts++;
            guessHistory.add(guess);

            if (guess < numberToGuess) {
                System.out.println("太小了，再试一次。");
            } else if (guess > numberToGuess) {
                System.out.println("太高了，再试一次。");
            } else {
                long elapsedTime = System.currentTimeMillis() - startTime;
                System.out.println("恭喜，你猜中了该数字！");
                System.out.println("你一共尝试了 " + attempts + " 次。");
                showElapsedTime(elapsedTime);
                showGuessHistory(guessHistory);
                break;
            }

            if (attempts >= 10) {
                System.out.println("您已经达到了最高猜数次数限制。");
                System.out.println("正确的数字是: " + numberToGuess);
                showGuessHistory(guessHistory);
                break;
            }
        }
    }

    /*
    根据用户输入选择不同的难度范围。
    case 4：调用 getCustomDifficulty() 让用户输入自定义最大值
    */
    private static int getDifficultyLevel(Scanner scanner) {
        System.out.print("请输入您的选择 (1/2/3/4): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return 50;
            case 2:
                return 100;
            case 3:
                return 200;
            case 4:
                return getCustomDifficulty(scanner);
            default:
                System.out.println("无效选择，默认使用中等难度 (1-100)。");
                return 100;
        }
    }

    //允许用户输入自定义的难度上限，确保输入的数字大于1。
    private static int getCustomDifficulty(Scanner scanner) {
        System.out.print("请输入自定义最大值 (必须大于 1): ");
        int max = scanner.nextInt();
        while (max <= 1) {
            System.out.print("无效输入，请输入一个大于 1 的数字: ");
            max = scanner.nextInt();
        }
        return max;
    }

    //使用 Random 类生成一个在 [1, difficultyLevel] 范围内的随机整数。
    private static int generateRandomNumber(int difficultyLevel) {
        return new Random().nextInt(difficultyLevel) + 1;
    }


    /*
    验证用户输入是否为整数，避免程序崩溃。
    如果输入无效，丢弃并重新要求输入。
    */
    private static int getValidatedGuess(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("无效输入，请输入一个整数: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    //询问用户是否要再玩一次游戏，返回 true 或 false。
    private static boolean askPlayAgain(Scanner scanner) {
        System.out.print("想再玩一次吗？(y/n): ");
        String choice = scanner.next().trim().toLowerCase();
        return choice.equals("y");
    }

    //计算并显示玩家完成游戏的总耗时，单位为秒。
    private static void showElapsedTime(long elapsedTime) {
        long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
        System.out.println("用时: " + seconds + " 秒。");
    }

    //遍历并打印玩家的所有猜测历史。
    private static void showGuessHistory(List<Integer> guessHistory) {
        System.out.println("\n您的猜测历史:");
        for (int i = 0; i < guessHistory.size(); i++) {
            System.out.println((i + 1) + ". " + guessHistory.get(i));
        }
    }
}
