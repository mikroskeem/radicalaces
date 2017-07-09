package com.radicalplay.aces;

import java.awt.Graphics;

public class Tank {
    cControl u = new cControl();
    int rspeed = 0;
    int ltyp = 0;
    float speed = 0.0F;
    boolean pexp = false;
    boolean left = false;
    boolean right = false;
    int[] lx = new int[20];
    int[] ly = new int[20];
    int[] lz = new int[20];
    int[] lxz = new int[20];
    int[] lzy = new int[20];
    int[] lxy = new int[20];
    int[] lstage = new int[20];
    int[] lspeed = new int[20];
    int[] lhit = new int[20];
    int[] nf = new int[20];
    int nl = 0;
    Lasers lsr;
    boolean skip = false;
    int bulkc = 0;
    int[] sms = new int[4];
    int[] sx = new int[4];
    int[] sy = new int[4];
    int[] sz = new int[4];
    int[] sxz = new int[4];
    int ns = 0;
    boolean smoke = false;
    int turnat = (int)(Math.random() * 50.0D);
    int tcnt = 0;
    int gxz = 0;
    int attack = 0;
    boolean responce = false;
    int trgxz = 180;
    int trgt = 0;

    public void preform(ContO var1, ContO[] var2, int var3, int var4) {
        int var5;
        for(var5 = Math.abs(var1.zy); var5 > 270; var5 -= 360) {
            ;
        }

        if (var5 > 90) {
            if (var1.xy < 180) {
                ++var1.xy;
                this.smoke = true;
            }

            if (var1.xy > 180) {
                var1.xy += -1;
                this.smoke = true;
            }
        } else {
            if (var1.xy < 0) {
                ++var1.xy;
                this.smoke = true;
            }

            if (var1.xy > 0) {
                var1.xy += -1;
                this.smoke = true;
            }
        }

        int var6;
        for(var6 = var1.zy; var6 > 90; var6 -= 180) {
            ;
        }

        while(var6 < -90) {
            var6 += 180;
        }

        if (var6 > 0) {
            if (var6 > 4) {
                var1.zy -= 2;
            } else {
                var1.zy += -1;
            }
        }

        if (var6 < 0) {
            if (var6 < -4) {
                var1.zy += 2;
            } else {
                ++var1.zy;
            }
        }

        if (this.u.left) {
            var1.xz += 5;
            if ((var1.xy == 0 || var1.xy == 180) && !this.left) {
                var1.xy = (int)((float)var1.xy + this.speed / 5.0F);
                this.left = true;
            }
        } else if (this.left) {
            this.left = false;
        }

        if (this.u.right) {
            var1.xz -= 5;
            if ((var1.xy == 0 || var1.xy == 180) && !this.right) {
                var1.xy = (int)((float)var1.xy - this.speed / 5.0F);
                this.right = true;
            }
        } else if (this.right) {
            this.right = false;
        }

        if (var1.x < -40000) {
            var1.x = -40000;
        }

        if (var1.x > 40000) {
            var1.x = 40000;
        }

        if (var1.z > 40000) {
            var1.z = 40000;
        }

        if (var1.z < -40000) {
            var1.z = -40000;
        }

        if (!this.pexp && var1.exp) {
            if (var1.nhits < var1.maxhits) {
                var1.exp = false;
                if (this.u.left) {
                    var1.xz += 5;
                } else {
                    var1.xz -= 5;
                }

                var1.xy += 15 - (int)(Math.random() * 30.0D);
                var1.zy += 5 + (int)(Math.random() * 5.0D);
                var1.y -= 30 + (int)(Math.random() * 15.0D);
            } else {
                this.pexp = true;
            }
        }

        if (this.pexp) {
            if (this.speed > 0.0F) {
                this.speed = (float)((double)this.speed - 0.3D);
            }

            if (this.speed < 0.0F) {
                this.speed = (float)((double)this.speed + 0.3D);
            }
        } else {
            if (this.speed > (float)this.rspeed) {
                this.speed = (float)((double)this.speed - 0.2D);
            }

            if (this.speed < (float)this.rspeed) {
                ++this.speed;
            }

            if (var1.y > 240) {
                var1.y = 240;
            } else if (var1.y > 235) {
                ++var1.y;
            } else {
                var1.y += 5;
            }
        }

        if (this.u.fire && !var1.exp) {
            if (this.skip && this.bulkc < this.lsr.srate[this.ltyp]) {
                this.lx[this.nl] = var1.x;
                this.ly[this.nl] = var1.y;
                this.lz[this.nl] = var1.z;
                this.lxz[this.nl] = var1.xz;
                this.lzy[this.nl] = var1.zy + 10;
                this.lxy[this.nl] = var1.xy;
                if (this.ly[this.nl] > 215) {
                    this.ly[this.nl] = 215;
                }

                this.lspeed[this.nl] = (int)((float)this.lsr.speed[this.ltyp] + this.speed);
                this.lstage[this.nl] = 1;
                this.lhit[this.nl] = 0;
                this.nf[this.nl] = 0;
                ++this.nl;
                if (this.nl == 20) {
                    this.nl = 0;
                }

                this.skip = false;
            } else if (!this.skip) {
                this.skip = true;
            }

            ++this.bulkc;
            if (this.bulkc > 12) {
                this.bulkc = 0;
            }
        }

        int var7 = 0;
        int var8 = 0;

        while(true) {
            if (this.lstage[var8] != 0) {
                ++var7;
                if (this.ly[var8] > 240 && this.lhit[var8] == 0) {
                    this.lhit[var8] = 1;
                }

                if (this.lhit[var8] == 0) {
                    if (this.lstage[var8] > 10 && this.nf[var8] < 15) {
                        int var9 = -1;
                        int var10 = -1;
                        if (!var2[var3].exp) {
                            var9 = this.getpy(var2[var3].x, var2[var3].y, var2[var3].z, var8);
                            var10 = var3;
                        }

                        for(int var11 = var4; var11 < var4 + 13; ++var11) {
                            int var12 = this.getpy(var2[var11].x, var2[var11].y, var2[var11].z, var8);
                            if (var12 < var9 && var12 > 0 && !var2[var11].exp) {
                                var9 = var12;
                                var10 = var11;
                            }
                        }

                        if (var9 < 22500 && var9 > 0) {
                            if (this.lspeed[var8] > 230) {
                                this.lspeed[var8] = 230;
                            }

                            int var22 = var2[var10].x;
                            int var23 = var2[var10].z;
                            int var13 = var2[var10].y;
                            short var14 = 0;
                            if (var22 - this.lx[var8] > 0) {
                                var14 = 180;
                            }

                            this.lxz[var8] = (int)((double)(90 + var14) + Math.atan((double)(var23 - this.lz[var8]) / (double)(var22 - this.lx[var8])) / 0.017453292519943295D);
                            var14 = 0;
                            if (var13 - this.ly[var8] < 0) {
                                var14 = -180;
                            }

                            int var15 = (int)Math.sqrt((double)((var23 - this.lz[var8]) * (var23 - this.lz[var8]) + (var22 - this.lx[var8]) * (var22 - this.lx[var8])));
                            this.lzy[var8] = -((int)((double)(90 + var14) - Math.atan((double)var15 / (double)(var13 - this.ly[var8])) / 0.017453292519943295D));
                            ++this.nf[var8];
                        }
                    }

                    this.lx[var8] -= (int)((float)this.lspeed[var8] * var1.m.cs.getsin(this.lxz[var8]) * var1.m.cs.getcos(this.lzy[var8]));
                    this.lz[var8] += (int)((float)this.lspeed[var8] * var1.m.cs.getcos(this.lxz[var8]) * var1.m.cs.getcos(this.lzy[var8]));
                    this.ly[var8] -= (int)((float)this.lspeed[var8] * var1.m.cs.getsin(this.lzy[var8]));
                    ++this.lstage[var8];
                    if (this.lstage[var8] > 80) {
                        this.lstage[var8] = 0;
                    }
                }
            }

            ++var8;
            if (var8 >= 20) {
                break;
            }
        }

        if (var7 != 0) {
            if (!var1.fire) {
                var1.fire = true;
            }
        } else if (var1.fire) {
            var1.fire = false;
        }

        var1.x -= (int)(this.speed * var1.m.cs.getsin(var1.xz) * var1.m.cs.getcos(var1.zy));
        var1.z += (int)(this.speed * var1.m.cs.getcos(var1.xz) * var1.m.cs.getcos(var1.zy));
        var1.y -= (int)(this.speed * var1.m.cs.getsin(var1.zy));
        if (this.tcnt > this.turnat) {
            if (this.trgt != 0) {
                this.trgt = 0;
            }

            short var19 = 0;
            if (var2[var4 + 4].x - var1.x > 0) {
                var19 = 180;
            }

            this.gxz = (int)((double)(90 + var19) + Math.atan((double)(var2[var4 + 4].z - var1.z) / (double)(var2[var4 + 4].x - var1.x)) / 0.017453292519943295D);
            this.turnat = (int)(Math.random() * 200.0D);
            var8 = this.getcpy(var2[var4 + 4], var1);
            if (var8 < 1500 && var8 > 0) {
                if (Math.random() > 0.5D) {
                    this.gxz += (int)(70.0D + Math.random() * 20.0D);
                } else {
                    this.gxz -= (int)(70.0D + Math.random() * 20.0D);
                }
            } else {
                this.gxz += (int)(Math.random() * 40.0D - 20.0D);
                this.trgt = 1;
            }

            var8 = this.getcpy(var2[var3], var1);
            if (var8 < 15000 && var8 > 0 && !var2[var3].exp) {
                if (this.attack == 0) {
                    if (Math.random() > 0.5D) {
                        this.attack = 1;
                    } else {
                        this.attack = 2;
                    }
                }

                if (this.attack == 1) {
                    var19 = 0;
                    if (var2[var3].x - var1.x > 0) {
                        var19 = 180;
                    }

                    this.gxz = (int)((double)(90 + var19) + Math.atan((double)(var2[var3].z - var1.z) / (double)(var2[var3].x - var1.x)) / 0.017453292519943295D);
                    this.turnat = (int)(Math.random() * 3.0D);
                    this.trgt = 2;
                }
            } else if (this.attack != 0) {
                this.attack = 0;
            }

            if (this.gxz >= 360) {
                this.gxz -= 360;
            }

            if (this.gxz < 0) {
                this.gxz += 360;
            }

            this.tcnt = 0;
        } else {
            ++this.tcnt;
        }

        if (var1.hit && Math.random() > 0.5D) {
            this.attack = 1;
            this.turnat = (int)(Math.random() * 10.0D);
        }

        if (this.u.fire) {
            this.u.fire = false;
        }

        if (this.trgt == 1 && this.trgxz < 90) {
            var8 = this.getcpy(var2[var4 + 4], var1);
            if (var8 > 0 && var8 < 10000) {
                this.u.fire = true;
            }
        }

        if (this.trgt == 2 && this.trgxz < 90) {
            this.u.fire = true;
        }

        if (this.responce) {
            if (this.u.left) {
                this.u.left = false;
            }

            if (this.u.right) {
                this.u.right = false;
            }

            int var21;
            for(var21 = var1.xz; var21 >= 360; var21 -= 360) {
                ;
            }

            while(var21 < 0) {
                var21 += 360;
            }

            if (Math.abs(var21 - this.gxz) > 5) {
                if (var21 > 270 && this.gxz < 90) {
                    this.u.left = true;
                    this.trgxz = 360 - var21 + this.gxz;
                } else if (var21 < 90 && this.gxz > 270) {
                    this.u.right = true;
                    this.trgxz = 360 - this.gxz + var21;
                } else if (var21 < this.gxz) {
                    this.u.left = true;
                    this.trgxz = this.gxz - var21;
                } else {
                    this.u.right = true;
                    this.trgxz = var21 - this.gxz;
                }
            }

            this.responce = false;
        } else {
            this.responce = true;
        }

    }

    public void dosmokes(Graphics var1, ContO var2) {
        if (var2.y > 200) {
            if (this.smoke && !var2.exp && this.sms[this.ns] == -1) {
                this.sx[this.ns] = var2.x + (int)(Math.random() * 150.0D - 75.0D);
                this.sy[this.ns] = var2.y + 10;
                this.sz[this.ns] = var2.z;
                this.sxz[this.ns] = var2.xz;
                this.sms[this.ns] = 0;
                ++this.ns;
                if (this.ns == 4) {
                    this.ns = 0;
                }

                this.smoke = false;
            }

            int var3 = 0;

            while(true) {
                if (this.sms[var3] != -1) {
                    if (this.sms[var3] < 5) {
                        this.lsr.gsmoke(var1, this.sx[var3], this.sy[var3], this.sz[var3], this.sxz[var3], 0, this.sms[var3]);
                    }

                    this.sy[var3] -= 10;
                    ++this.sms[var3];
                    if (this.sms[var3] == 10) {
                        this.sms[var3] = -1;
                    }
                }

                ++var3;
                if (var3 >= 4) {
                    break;
                }
            }
        }

    }

    public void reset(int var1, int var2) {
        this.rspeed = var1;
        this.pexp = false;
        this.ltyp = var2;
        int var3 = 0;

        while(true) {
            this.lstage[var3] = 0;
            ++var3;
            if (var3 >= 20) {
                break;
            }
        }

    }

    public Tank(Medium var1) {
        this.lsr = new Lasers(var1);
        int var2 = 0;

        while(true) {
            this.sms[var2] = -1;
            ++var2;
            if (var2 >= 4) {
                break;
            }
        }

    }

    public void lasercolid(ContO var1) {
        if (!var1.exp && !var1.out) {
            int var2 = 0;

            while(true) {
                if (this.lstage[var2] != 0 && this.lhit[var2] == 0) {
                    int var3 = this.getpy(var1.x, var1.y, var1.z, var2);
                    if (var3 < var1.maxR / 10 * (var1.maxR / 10) && var3 > 0) {
                        if (var1.rcol != 0 && var3 < var1.maxR / (10 * var1.rcol) * (var1.maxR / (10 * var1.rcol)) + this.lsr.rads[this.ltyp] / 10 * (this.lsr.rads[this.ltyp] / 10)) {
                            this.lhit[var2] = 1;
                            if (var1.maxhits != -1) {
                                var1.hit = true;
                                var1.nhits += this.lsr.damg[this.ltyp];
                            }
                        }

                        if (var1.pcol != 0) {
                            for(int var4 = 0; var4 < var1.npl; ++var4) {
                                for(int var5 = 0; var5 < var1.p[var4].n; ++var5) {
                                    if (!var1.hit && (this.lx[var2] - (var1.x + var1.p[var4].ox[var5])) * (this.lx[var2] - (var1.x + var1.p[var4].ox[var5])) + (this.ly[var2] - (var1.y + var1.p[var4].oy[var5])) * (this.ly[var2] - (var1.y + var1.p[var4].oy[var5])) + (this.lz[var2] - (var1.z + var1.p[var4].oz[var5])) * (this.lz[var2] - (var1.z + var1.p[var4].oz[var5])) < this.lsr.rads[this.ltyp] * 10 / var1.pcol * (this.lsr.rads[this.ltyp] * 10 / var1.pcol)) {
                                        this.lhit[var2] = 1;
                                        if (var1.maxhits != -1) {
                                            var1.hit = true;
                                            var1.nhits += this.lsr.damg[this.ltyp];
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                ++var2;
                if (var2 >= 20) {
                    break;
                }
            }
        }

    }

    public int getpy(int var1, int var2, int var3, int var4) {
        return (var1 - this.lx[var4]) / 10 * ((var1 - this.lx[var4]) / 10) + (var2 - this.ly[var4]) / 10 * ((var2 - this.ly[var4]) / 10) + (var3 - this.lz[var4]) / 10 * ((var3 - this.lz[var4]) / 10);
    }

    public int getcpy(ContO var1, ContO var2) {
        return (var1.x - var2.x) / 100 * ((var1.x - var2.x) / 100) + (var1.y - var2.y) / 100 * ((var1.y - var2.y) / 100) + (var1.z - var2.z) / 100 * ((var1.z - var2.z) / 100);
    }

    public void dl(Graphics var1) {
        int var2 = 0;

        while(true) {
            if (this.lstage[var2] != 0) {
                this.lsr.d(var1, this.ltyp, this.lx[var2], this.ly[var2], this.lz[var2], this.lxz[var2], this.lzy[var2], this.lxy[var2], this.lhit[var2]);
                if (this.lhit[var2] != 0) {
                    ++this.lhit[var2];
                    if (this.lhit[var2] > 2) {
                        this.lstage[var2] = 0;
                    }
                }
            }

            ++var2;
            if (var2 >= 20) {
                break;
            }
        }

    }
}
