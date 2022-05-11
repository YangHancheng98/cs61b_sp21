public class Solution {
    public static void main(String[] args) {
        double[][] x = new double[7][3];
        x[0] = new double[]{1,-1,3};
        x[1] = new double[]{1,0,2};
        x[2] = new double[]{1,0,1};
        x[3] = new double[]{1,0,0};
        x[4] = new double[]{1,1,5};
        x[5] = new double[]{1,1,6};
        x[6] = new double[]{1,3,3};

        double[] y = new double[]{1,1,1,1,0,0,0};

        double alpha = 0.8;
        double[][] w = new double[7][3];
        w[0] = new double[]{0,0,0};
        for(int i = 0; i < 10000; i++){
            double average_y;
            if (x[i%7][0]*w[i%7][0]+x[i%7][1]*w[i%7][1]+x[i%7][2]*w[i%7][2]>0){
                average_y = 1;
            }
            else average_y = 0;

            double w_iplus1_0, w_iplus1_1, w_iplus1_2;

            if (y[i%7] != average_y) {
                w_iplus1_0 = w[i%7][0] + alpha * (y[i%7]-average_y) * x[i%7][0];
                w_iplus1_1 = w[i%7][1] + alpha * (y[i%7]-average_y) * x[i%7][1];
                w_iplus1_2 = w[i%7][2] + alpha * (y[i%7]-average_y) * x[i%7][2];
            }
            else{
                w_iplus1_0 = w[i%7][0];
                w_iplus1_1 = w[i%7][1];
                w_iplus1_2 = w[i%7][2];
            }
            w[(i+1)%7] = new double[]{w_iplus1_0,w_iplus1_1,w_iplus1_2};
        }

    }



}
