package com.radicalplay.aces;

import java.awt.Color;
import java.awt.Graphics;

public class Medium {
    boolean isun = false;
    SinCos cs = new SinCos();
    int focus_point = 400;
    int ground = 250;
    int er = 0;
    int eg = 0;
    int eb = 0;
    int jumping = 0;
    int cx = 250;
    int cy = 150;
    int cz = 50;
    int xz = 0;
    int zy = 0;
    int x = 3000;
    int y = -1000;
    int z = -2000;
    int w = 500;
    int h = 360;
    int tart = 0;
    int yart = -100;
    int zart = 0;
    int ztgo = 0;
    boolean td = false;
    int vxz = 0;
    int adv = -500;
    boolean vert = false;

    public int ys(int var1, int var2) {
        if (var2 < 10) {
            var2 = 10;
        }

        return (var2 - this.focus_point) * (this.cy - var1) / var2 + var1;
    }

    public void infront(ContO var1) {
        int var2 = var1.zy;

        int var3;
        for(var3 = var1.xz; var2 > 360; var2 -= 360) {
            ;
        }

        while(var2 < 0) {
            var2 += 360;
        }

        if (var2 > 90 && var2 < 270) {
            this.tart += (180 - this.tart) / 3;
            this.yart += (100 - this.yart) / 3;
        } else {
            this.tart -= this.tart / 3;
            this.yart += (-100 - this.yart) / 3;
        }

        var3 = var3 + this.tart;
        if (var2 > 90) {
            var2 = 180 - var2;
        }

        if (var2 < -90) {
            var2 = -180 - var2;
        }

        int var4 = var1.y + (int)((float)(var1.y + this.yart - var1.y) * this.cs.getcos(var1.zy) - (float)(var1.z + 800 - var1.z) * this.cs.getsin(var1.zy));
        int var5 = var1.z + (int)((float)(var1.y + this.yart - var1.y) * this.cs.getsin(var1.zy) + (float)(var1.z + 800 - var1.z) * this.cs.getcos(var1.zy));
        int var6 = var1.x + (int)((float)(-(var5 - var1.z)) * this.cs.getsin(var1.xz));
        int var7 = var1.z + (int)((float)(var5 - var1.z) * this.cs.getcos(var1.xz));
        this.zy = var2;
        this.xz = -(var3 + 180);
        this.x += (var6 - this.cx - this.x) / 3;
        this.z = (int)((double)this.z + (double)(var7 - this.cz - this.z) / 1.5D);
        this.y = (int)((double)this.y + (double)(var4 - this.cy - this.y) / 1.5D);
    }

    public void d(Graphics var1) {
        if (this.zy > 90) {
            this.zy = 90;
        }

        if (this.zy < -90) {
            this.zy = -90;
        }

        if (this.y > 0) {
            this.y = 0;
        }

        this.ground = 250 - this.y;
        int var2 = 70000;
        int var3 = 250;
        if (this.zy != 0) {
            var3 = this.cy + (int)((float)(250 - this.cy) * this.cs.getcos(this.zy) - (float)(70000 - this.cz) * this.cs.getsin(this.zy));
            var2 = this.cz + (int)((float)(250 - this.cy) * this.cs.getsin(this.zy) + (float)(70000 - this.cz) * this.cs.getcos(this.zy));
        }

        int[] var4 = new int[4];
        int[] var5 = new int[4];
        var4[0] = 0;
        var5[0] = 0;
        var4[1] = this.w;
        var5[1] = 0;
        var4[2] = this.w;
        var5[2] = this.ys(var3, var2);
        if (var5[2] > this.h) {
            var5[2] = this.h;
        }

        var4[3] = 0;
        var5[3] = var5[2];
        if (var5[2] > 0) {
            if (this.jumping != 0) {
                if (this.jumping == 3) {
                    var5[2] = this.h;
                    var5[3] = this.h;
                    var1.setColor(new Color(240, 240, 240));
                    var1.fillPolygon(var4, var5, 4);
                }
            } else {
                if (!this.isun) {
                    var1.setColor(new Color(159 + 52 * this.er, 180 + 56 * this.eg, 189 + 58 * this.eb));
                } else {
                    var1.setColor(new Color(159 + 52 * this.er, 176 + 56 * this.eg, 191 + 58 * this.eb));
                }

                var1.fillPolygon(var4, var5, 4);
            }
        }

        var4[0] = -1;
        var5[0] = this.ys(var3, var2);
        if (var5[0] < 0) {
            var5[0] = -1;
        }

        var4[1] = -1;
        var5[1] = this.h;
        var4[2] = this.w;
        var5[2] = this.h;
        var4[3] = this.w;
        var5[3] = var5[0];
        if (var5[0] < this.h && this.jumping == 0) {
            if (!this.isun) {
                var1.setColor(new Color(177 + 55 * this.er, 154 + 50 * this.eg, 120 + 44 * this.eb));
            } else {
                var1.setColor(new Color(175 + 55 * this.er, 151 + 50 * this.eg, 112 + 44 * this.eb));
            }

            var1.fillPolygon(var4, var5, 4);
            var4[1] = -1;
            var5[1] = var5[0];
            var4[0] = -1;
            var5[0] -= 3;
            var4[2] = this.w;
            var5[2] = var5[1];
            var4[3] = this.w;
            var5[3] = var5[0];
            if (!this.isun) {
                var1.setColor(new Color(169 + 55 * this.er, 171 + 50 * this.eg, 160 + 44 * this.eb));
            } else {
                var1.setColor(new Color(167 + 55 * this.er, 164 + 50 * this.eg, 151 + 44 * this.eb));
            }

            var1.fillPolygon(var4, var5, 4);
        }

        if (this.jumping != 0) {
            this.jumping += -1;
        }

    }

    public void watch(ContO var1) {
        if (!this.td) {
            this.y = var1.y + (int)((float)(var1.y - 300 - var1.y) * this.cs.getcos(var1.zy) - (float)(var1.z + 3000 - var1.z) * this.cs.getsin(var1.zy));
            int var2 = var1.z + (int)((float)(var1.y - 300 - var1.y) * this.cs.getsin(var1.zy) + (float)(var1.z + 3000 - var1.z) * this.cs.getcos(var1.zy));
            this.x = var1.x + (int)((float)(var1.x + 400 - var1.x) * this.cs.getcos(var1.xz) - (float)(var2 - var1.z) * this.cs.getsin(var1.xz));
            this.z = var1.z + (int)((float)(var1.x + 400 - var1.x) * this.cs.getsin(var1.xz) + (float)(var2 - var1.z) * this.cs.getcos(var1.xz));
            this.td = true;
        }

        short var6 = 0;
        if (var1.x - this.x - this.cx > 0) {
            var6 = 180;
        }

        int var3 = -((int)((double)(90 + var6) + Math.atan((double)(var1.z - this.z) / (double)(var1.x - this.x - this.cx)) / 0.017453292519943295D));
        var6 = 0;
        if (var1.y - this.y - this.cy < 0) {
            var6 = -180;
        }

        int var4 = (int)Math.sqrt((double)((var1.z - this.z) * (var1.z - this.z) + (var1.x - this.x - this.cx) * (var1.x - this.x - this.cx)));
        int var5 = (int)((double)(90 + var6) - Math.atan((double)var4 / (double)(var1.y - this.y - this.cy)) / 0.017453292519943295D);
        this.xz = var3;
        this.zy += (var5 - this.zy) / 5;
        if ((int)Math.sqrt((double)((var1.z - this.z) * (var1.z - this.z) + (var1.x - this.x - this.cx) * (var1.x - this.x - this.cx) + (var1.y - this.y - this.cy) * (var1.y - this.y - this.cy))) > 3500) {
            this.td = false;
        }

    }

    public void around(ContO var1, int var2) {
        byte var3 = 1;
        if (var2 == 6000) {
            var3 = 2;
        }

        this.y = var1.y + this.adv;
        this.x = var1.x + (int)((float)(var1.x - var2 + this.adv * var3 - var1.x) * this.cs.getcos(this.vxz));
        this.z = var1.z + (int)((float)(var1.x - var2 + this.adv * var3 - var1.x) * this.cs.getsin(this.vxz));
        if (var2 == 6000) {
            if (!this.vert) {
                this.adv -= 10;
            } else {
                this.adv += 10;
            }

            if (this.adv < -900) {
                this.vert = true;
            }

            if (this.adv > 1200) {
                this.vert = false;
            }
        } else {
            if (!this.vert) {
                this.adv -= 2;
            } else {
                this.adv += 2;
            }

            if (this.adv < -500) {
                this.vert = true;
            }

            if (this.adv > 150) {
                this.vert = false;
            }

            if (this.adv > 300) {
                this.adv = 300;
            }
        }

        this.vxz += 2;
        if (this.vxz > 360) {
            this.vxz -= 360;
        }

        short var4 = 0;
        int var5 = this.y;
        if (var5 > 0) {
            var5 = 0;
        }

        if (var1.y - var5 - this.cy < 0) {
            var4 = -180;
        }

        int var6 = (int)Math.sqrt((double)((var1.z - this.z) * (var1.z - this.z) + (var1.x - this.x - this.cx) * (var1.x - this.x - this.cx)));
        int var7 = (int)((double)(90 + var4) - Math.atan((double)var6 / (double)(var1.y - var5 - this.cy)) / 0.017453292519943295D);
        this.xz = -this.vxz + 90;
        this.zy += (var7 - this.zy) / 10;
    }

    public void left(ContO var1) {
        int var2 = var1.y;
        int var3 = var1.x + (int)((float)(var1.x + 600 - var1.x) * this.cs.getcos(var1.xz));
        int var4 = var1.z + (int)((float)(var1.x + 600 - var1.x) * this.cs.getsin(var1.xz));
        this.zy = 0;
        this.xz = -(var1.xz + 90);
        this.x = (int)((double)this.x + (double)(var3 - this.cx - this.x) / 1.5D);
        this.z = (int)((double)this.z + (double)(var4 - this.cz - this.z) / 1.5D);
        this.y = (int)((double)this.y + (double)(var2 - this.cy - this.y) / 1.5D);
    }

    public void right(ContO var1) {
        int var2 = var1.y;
        int var3 = var1.x + (int)((float)(var1.x - 600 - var1.x) * this.cs.getcos(var1.xz));
        int var4 = var1.z + (int)((float)(var1.x - 600 - var1.x) * this.cs.getsin(var1.xz));
        this.zy = 0;
        this.xz = -(var1.xz - 90);
        this.x += (var3 - this.cx - this.x) / 3;
        this.z = (int)((double)this.z + (double)(var4 - this.cz - this.z) / 1.5D);
        this.y = (int)((double)this.y + (double)(var2 - this.cy - this.y) / 1.5D);
    }

    public void behinde(ContO var1) {
        int var2 = var1.zy;

        int var3;
        for(var3 = var1.xz; var2 > 360; var2 -= 360) {
            ;
        }

        while(var2 < 0) {
            var2 += 360;
        }

        if (var2 > 90 && var2 < 270) {
            this.tart += (180 - this.tart) / 3;
            this.yart += (100 - this.yart) / 4;
        } else {
            this.tart -= this.tart / 3;
            this.yart += (-100 - this.yart) / 4;
        }

        var3 = var3 + this.tart;
        if (var2 > 90) {
            var2 = 180 - var2;
        }

        if (var2 < -90) {
            var2 = -180 - var2;
        }

        int var4 = var1.y + (int)((float)(var1.y + this.yart - var1.y) * this.cs.getcos(var1.zy) - (float)(var1.z - 600 - var1.z) * this.cs.getsin(var1.zy));
        int var5 = var1.z + (int)((float)(var1.y + this.yart - var1.y) * this.cs.getsin(var1.zy) + (float)(var1.z - 600 - var1.z) * this.cs.getcos(var1.zy));
        int var6 = var1.x + (int)((float)(-(var5 - var1.z)) * this.cs.getsin(var1.xz));
        int var7 = var1.z + (int)((float)(var5 - var1.z) * this.cs.getcos(var1.xz));
        this.zy = -var2;
        this.xz = -var3;
        this.x += (var6 - this.cx - this.x) / 3;
        this.z = (int)((double)this.z + (double)(var7 - this.cz - this.z) / 1.5D);
        this.y = (int)((double)this.y + (double)(var4 - this.cy - this.y) / 1.5D);
    }
}
