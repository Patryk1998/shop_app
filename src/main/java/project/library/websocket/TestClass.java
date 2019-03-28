package project.library.websocket;

public class TestClass {

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        int a = 8;
        int b = 6;
        int[] array = new int[] {2, 1, 1, 2, 2, 2, 2, 2};
        System.out.print(testClass.solution(a,b,array));
    }

    public String solution(int topSum, int bottomSum, int[] A) {

        StringBuilder resultTop = new StringBuilder();
        StringBuilder resultBottom = new StringBuilder();
        int tS = topSum;
        int bS = bottomSum;
        int arraySum = 0;

        for(int i=0; i<A.length; i++) {
            arraySum += A[i];
        }

        if(tS+bS != arraySum) {
            return "IMPOSSIBLE";
        }

        for(int i=0; i<A.length; i++) {
            switch (A[i]) {
                case 2: {
                    tS--;
                    bS--;
                    resultTop.append("1");
                    resultBottom.append("1");
                    break;
                }

                case 1: {
                    if(tS<bS) {
                        bS--;
                        resultBottom.append("1");
                        resultTop.append("0");
                    }else {
                        tS--;
                        resultTop.append("1");
                        resultBottom.append("0");
                    }
                    break;
                }

                case 0: {
                    resultTop.append("0");
                    resultBottom.append("0");
                }
            }
        }

        return resultTop.toString() + "," + resultBottom.toString();
    }
}
