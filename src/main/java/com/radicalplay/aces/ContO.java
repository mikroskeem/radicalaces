package com.radicalplay.aces;

import java.awt.Graphics;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class ContO {
    Medium m;
    Plane[] p;
    int npl = 0;
    int x = 0;
    int y = 0;
    int z = 0;
    int xz = 0;
    int xy = 0;
    int zy = 0;
    int dist = 0;
    int maxR = 0;
    int disp = 0;
    boolean shadow = false;
    boolean loom = false;
    int grounded = 1;
    boolean colides = false;
    int rcol = 0;
    int pcol = 0;
    boolean out = false;
    boolean fire = false;
    boolean hit = false;
    int nhits = 0;
    int maxhits = -1;
    boolean wire = false;
    boolean exp = false;

    public int ys(int var1, int var2) {
        if (var2 < 10) {
            var2 = 10;
        }

        return (var2 - this.m.focus_point) * (this.m.cy - var1) / var2 + var1;
    }

    public void reset() {
        this.exp = false;
        this.nhits = 0;
        this.xz = 0;
        this.xy = 0;
        this.zy = 0;
    }

    public ContO(byte[] var1, Medium var2, int var3, int var4, int var5) {
        this.m = var2;
        this.p = new Plane[100];
        this.x = var3;
        this.y = var4;
        this.z = var5;
        boolean var8 = false;
        int var9 = 0;
        float var10 = 1.0F;
        int[] var11 = new int[100];
        int[] var12 = new int[100];
        int[] var13 = new int[100];
        int[] var14 = new int[]{50, 50, 50};

        try {
            DataInputStream var15 = new DataInputStream(new ByteArrayInputStream(var1));

            String var6;
            while((var6 = var15.readLine()) != null) {
                String var7 = "" + var6.trim();
                if (var7.startsWith("<p>")) {
                    var8 = true;
                    var9 = 0;
                }

                if (var8) {
                    if (var7.startsWith("c")) {
                        var14[0] = this.getvalue("c", var7, 0);
                        var14[1] = this.getvalue("c", var7, 1);
                        var14[2] = this.getvalue("c", var7, 2);
                    }

                    if (var7.startsWith("p")) {
                        var11[var9] = (int)((float)this.getvalue("p", var7, 0) * var10);
                        var12[var9] = (int)((float)this.getvalue("p", var7, 1) * var10);
                        var13[var9] = (int)((float)this.getvalue("p", var7, 2) * var10);
                        ++var9;
                    }
                }

                if (var7.startsWith("</p>")) {
                    this.p[this.npl] = new Plane(this.m, var11, var13, var12, var9, var14);
                    ++this.npl;
                    var8 = false;
                }

                if (var7.startsWith("MaxRadius")) {
                    this.maxR = this.getvalue("MaxRadius", var7, 0);
                }

                if (var7.startsWith("disp")) {
                    this.disp = this.getvalue("disp", var7, 0);
                }

                if (var7.startsWith("shadow")) {
                    this.shadow = true;
                }

                if (var7.startsWith("loom")) {
                    this.loom = true;
                }

                if (var7.startsWith("out")) {
                    this.out = true;
                }

                if (var7.startsWith("hits")) {
                    this.maxhits = this.getvalue("hits", var7, 0);
                }

                if (var7.startsWith("colid")) {
                    this.colides = true;
                    this.rcol = this.getvalue("colid", var7, 0);
                    this.pcol = this.getvalue("colid", var7, 1);
                }

                if (var7.startsWith("grounded")) {
                    this.grounded = this.getvalue("grounded", var7, 0);
                }

                if (var7.startsWith("div")) {
                    var10 = (float)this.getvalue("div", var7, 0) / 10.0F;
                }
            }

            var15.close();
        } catch (Exception var16) {
            ;
        }

    }

    public ContO(Medium var1, ContO var2, int var3, int var4, int var5) {
        this.m = var1;
        this.npl = var2.npl;
        this.maxR = var2.maxR;
        this.disp = var2.disp;
        this.loom = var2.loom;
        this.colides = var2.colides;
        this.maxhits = var2.maxhits;
        this.out = var2.out;
        this.rcol = var2.rcol;
        this.pcol = var2.pcol;
        this.shadow = var2.shadow;
        this.grounded = var2.grounded;
        this.p = new Plane[var2.npl];
        this.x = var3;
        this.y = var4;
        this.z = var5;

        for(int var6 = 0; var6 < this.npl; ++var6) {
            this.p[var6] = new Plane(this.m, var2.p[var6].ox, var2.p[var6].oz, var2.p[var6].oy, var2.p[var6].n, var2.p[var6].c);
        }

    }

    public void d(Graphics var1) {
        if (this.dist != 0) {
            this.dist = 0;
        }

        int var2 = 0;

        for(int var3 = 0; var3 < this.npl; ++var3) {
            if (!this.exp) {
                if (this.p[var3].exp != 0) {
                    this.p[var3].exp = 0;
                }
            } else if (this.p[var3].exp == 0) {
                this.p[var3].exp = 1;
            } else if (this.p[var3].exp == 7) {
                ++var2;
            }
        }

        if (!this.out && var2 != this.npl) {
            if (this.fire) {
                this.dist = 1;
            }

            int var10 = this.m.cx + (int)((float)(this.x - this.m.x - this.m.cx) * this.m.cs.getcos(this.m.xz) - (float)(this.z - this.m.z - this.m.cz) * this.m.cs.getsin(this.m.xz));
            int var4 = this.m.cz + (int)((float)(this.x - this.m.x - this.m.cx) * this.m.cs.getsin(this.m.xz) + (float)(this.z - this.m.z - this.m.cz) * this.m.cs.getcos(this.m.xz));
            int var5 = this.m.cz + (int)((float)(this.y - this.m.y - this.m.cy) * this.m.cs.getsin(this.m.zy) + (float)(var4 - this.m.cz) * this.m.cs.getcos(this.m.zy));
            if (this.xs(var10 + this.maxR, var5) > 0 && this.xs(var10 - this.maxR, var5) < this.m.w && var5 > -this.maxR && var5 < 50000 && this.xs(var10 + this.maxR, var5) - this.xs(var10 - this.maxR, var5) > this.disp || this.exp) {
                if (this.shadow || this.exp) {
                    int var6 = this.m.cy + (int)((float)(this.m.ground - this.m.cy) * this.m.cs.getcos(this.m.zy) - (float)(var4 - this.m.cz) * this.m.cs.getsin(this.m.zy));
                    int var7 = this.m.cz + (int)((float)(this.m.ground - this.m.cy) * this.m.cs.getsin(this.m.zy) + (float)(var4 - this.m.cz) * this.m.cs.getcos(this.m.zy));
                    if (this.ys(var6 + this.maxR, var7) > 0 && this.ys(var6 - this.maxR, var7) < this.m.h || this.exp) {
                        for(int var8 = 0; var8 < this.npl; ++var8) {
                            this.p[var8].s(var1, this.x - this.m.x, this.y - this.m.y, this.z - this.m.z, this.xz, this.xy, this.zy, this.loom);
                        }
                    }
                }

                int var11 = this.m.cy + (int)((float)(this.y - this.m.y - this.m.cy) * this.m.cs.getcos(this.m.zy) - (float)(var4 - this.m.cz) * this.m.cs.getsin(this.m.zy));
                if (this.ys(var11 + this.maxR, var5) > 0 && this.ys(var11 - this.maxR, var5) < this.m.h || this.exp) {
                    if (this.m.jumping != 0 && this.m.jumping < 4) {
                        this.hit = true;
                    }

                    int[] var12 = new int[this.npl];

                    for(int var13 = 0; var13 < this.npl; ++var13) {
                        var12[var13] = 0;

                        for(int var9 = 0; var9 < this.npl; ++var9) {
                            if (this.p[var13].av != this.p[var9].av) {
                                if (this.p[var13].av < this.p[var9].av) {
                                    ++var12[var13];
                                }
                            } else if (var13 > var9) {
                                ++var12[var13];
                            }
                        }
                    }

                    for(int var14 = 0; var14 < this.npl; ++var14) {
                        for(int var15 = 0; var15 < this.npl; ++var15) {
                            if (var12[var15] == var14) {
                                this.p[var15].d(var1, this.x - this.m.x, this.y - this.m.y, this.z - this.m.z, this.xz, this.xy, this.zy, this.hit, this.wire, this.loom);
                            }
                        }
                    }

                    this.dist = (int)Math.sqrt((double)((int)Math.sqrt((double)((this.m.x + this.m.cx - this.x) * (this.m.x + this.m.cx - this.x) + (this.m.z - this.z) * (this.m.z - this.z) + (this.m.y + this.m.cy - this.y) * (this.m.y + this.m.cy - this.y))))) * this.grounded;
                }
            }
        }

        if (this.hit) {
            this.hit = false;
            if (this.m.jumping == 0 && this.nhits > this.maxhits) {
                this.exp = true;
            }
        }

    }

    public void tryexp(ContO var1) {
        if (!var1.exp && !this.out && !this.exp) {
            int var2 = this.getpy(var1.x, var1.y, var1.z);
            if (var2 < this.maxR / 10 * (this.maxR / 10) + var1.maxR / 10 * (var1.maxR / 10) && var2 > 0) {
                if (this.pcol != 0) {
                    for(int var3 = 0; var3 < this.npl; ++var3) {
                        for(int var4 = 0; var4 < this.p[var3].n; ++var4) {
                            if ((var1.x - (this.x + this.p[var3].ox[var4])) * (var1.x - (this.x + this.p[var3].ox[var4])) + (var1.y - (this.y + this.p[var3].oy[var4])) * (var1.y - (this.y + this.p[var3].oy[var4])) + (var1.z - (this.z + this.p[var3].oz[var4])) * (var1.z - (this.z + this.p[var3].oz[var4])) < var1.maxR * 10 / this.pcol * (var1.maxR * 10 / this.pcol)) {
                                var1.exp = true;
                                break;
                            }
                        }
                    }
                }

                if (this.rcol != 0 && var2 < this.maxR / (10 * this.rcol) * (this.maxR / (10 * this.rcol)) + var1.maxR / 10 * (var1.maxR / 10)) {
                    var1.exp = true;
                }
            }
        }

    }

    public int getpy(int var1, int var2, int var3) {
        return (var1 - this.x) / 10 * ((var1 - this.x) / 10) + (var2 - this.y) / 10 * ((var2 - this.y) / 10) + (var3 - this.z) / 10 * ((var3 - this.z) / 10);
    }

    public void loadrots(boolean var1) {
        if (!var1) {
            this.reset();
        }

        for(int var2 = 0; var2 < this.npl; ++var2) {
            this.p[var2].rot(this.p[var2].ox, this.p[var2].oy, 0, 0, this.xy, this.p[var2].n);
            this.p[var2].rot(this.p[var2].oy, this.p[var2].oz, 0, 0, this.zy, this.p[var2].n);
            this.p[var2].rot(this.p[var2].ox, this.p[var2].oz, 0, 0, this.xz, this.p[var2].n);
            this.p[var2].loadprojf();
        }

        if (var1) {
            this.reset();
        }

    }

    public int getvalue(String var1, String var2, int var3) {
        int var5 = 0;
        String var7 = "";

        for(int var4 = var1.length() + 1; var4 < var2.length(); ++var4) {
            String var6 = "" + var2.charAt(var4);
            if (var6.equals(",") || var6.equals(")")) {
                ++var5;
                ++var4;
            }

            if (var5 == var3) {
                var7 = var7 + var2.charAt(var4);
            }
        }

        return Integer.valueOf(var7).intValue();
    }

    public int xs(int var1, int var2) {
        if (var2 < 10) {
            var2 = 10;
        }

        return (var2 - this.m.focus_point) * (this.m.cx - var1) / var2 + var1;
    }
}
