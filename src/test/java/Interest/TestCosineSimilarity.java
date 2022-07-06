package Interest;

import com.am.adastra.util.Interest.CosineSimilarity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestCosineSimilarity {
    @Autowired
    static CosineSimilarity cosineSimilarity;

    public static void main(String[] args) {
        double[] u1 = {5.0,6.0,1.0,0.0};
//        double[] u2 = {4,6.0,8,3};
        double[] u2 = {7,8.0,6,9};
//        double[] u2 = {9,9.0,3,8};
        double v = cosineSimilarity.check(u1, u2);
        System.out.println(v);
    }

}
