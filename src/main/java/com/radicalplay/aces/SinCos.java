package com.radicalplay.aces;

public class SinCos {
    float[] tcos = new float[360];
    float[] tsin = new float[360];

    public SinCos() {
        int var1 = 0;

        while(true) {
            this.tcos[var1] = (float)Math.cos((double)var1 * 0.017453292519943295D);
            ++var1;
            if (var1 >= 360) {
                break;
            }
        }

        var1 = 0;

        while(true) {
            this.tsin[var1] = (float)Math.sin((double)var1 * 0.017453292519943295D);
            ++var1;
            if (var1 >= 360) {
                break;
            }
        }

    }

    public float getsin(int var1) {
        while(var1 >= 360) {
            var1 -= 360;
        }

        while(var1 < 0) {
            var1 += 360;
        }

        return this.tsin[var1];
    }

    public float getcos(int var1) {
        while(var1 >= 360) {
            var1 -= 360;
        }

        while(var1 < 0) {
            var1 += 360;
        }

        return this.tcos[var1];
    }
}
