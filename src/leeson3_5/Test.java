package leeson3_5;

public class Test {
    public static void main(String[] args) {
        HUAWEI_MateBook laptop1 = new HUAWEI_MateBook();
        laptop1.showLogo();
        laptop1.startUp();
        laptop1.showLogo();
        laptop1.shutDown();

        XiaoMiBook laptop2 = new XiaoMiBook();
        laptop2.showLogo();
        laptop2.startUp();
        laptop2.showLogo();
        laptop2.shutDown();
    }

}
