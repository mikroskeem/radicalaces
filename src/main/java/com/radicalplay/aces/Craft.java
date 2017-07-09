package com.radicalplay.aces;

import java.awt.Graphics;

public class Craft {
    cControl u = new cControl();
    int rspeed = 0;
    float speed = 0.0F;
    int rlift = 0;
    double lift = 0.0D;
    boolean pexp = false;
    int ltyp = 3;
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
    int[] szy = new int[4];
    int ns = 0;
    boolean smoke = false;
    int[] dms = new int[4];
    int[] dx = new int[4];
    int[] dy = new int[4];
    int[] dz = new int[4];
    int[] dxz = new int[4];
    int[] dzy = new int[4];
    int nd = 0;
    int gxz = 0;
    int gzy = 0;
    boolean responce = false;
    int trgxz = 0;
    int trgzy = 0;
    int out = 0;
    int turnat = (int)(Math.random() * 50.0D);
    int tcnt = 0;
    boolean engage = true;
    int enx = 0;
    int eny = 0;
    int enz = 0;
    int ens = 4;
    boolean targeting = false;
    int mode = 0;
    int m3o = 0;
    int m3cnt = 0;
    int m1cnt = 0;
    int relax = 50;
    int runn = 30;
    int liftup = 500;
    boolean dracs = false;

    public void preform(ContO var1, ContO[] var2, int[] var3, int var4, int var5, int var6) {
        int var7;
        for(var7 = Math.abs(var1.zy); var7 > 360; var7 -= 360) {
            ;
        }

        byte var8 = 1;
        if (var7 > 90 && var7 < 270) {
            var8 = -1;
        }

        if (var1.y < 207) {
            if (this.u.up) {
                var1.zy -= (int)(5.0F * var1.m.cs.getcos(var1.xy));
                var1.xz += (int)((float)(var8 * 3) * var1.m.cs.getsin(var1.xy));
            }

            if (this.u.down) {
                var1.zy += (int)(5.0F * var1.m.cs.getcos(var1.xy));
                var1.xz -= (int)((float)(var8 * 3) * var1.m.cs.getsin(var1.xy));
            }
        } else {
            int var9;
            for(var9 = Math.abs(var1.zy); var9 > 90; var9 -= 180) {
                ;
            }

            int var10;
            for(var10 = Math.abs(var1.xy); var10 > 90; var10 -= 180) {
                ;
            }

            int var11;
            for(var11 = Math.abs(var1.zy); var11 > 270; var11 -= 360) {
                ;
            }

            int var12;
            for(var12 = Math.abs(var1.xy); var12 > 270; var12 -= 360) {
                ;
            }

            boolean var13 = Math.abs(var11) < 90 && Math.abs(var12) < 90 || Math.abs(var11) > 90 && Math.abs(var12) > 90;
            boolean var14 = Math.abs(var9) > 30 || Math.abs(var10) > 30;
            if ((!var13 || var14) && !var1.exp) {
                var1.exp = true;
                var1.y = 170;
                this.speed = 30.0F;
                this.pexp = true;
            }

            int var15;
            for(var15 = Math.abs(var1.zy); var15 > 270; var15 -= 360) {
                ;
            }

            if (var15 > 90) {
                var1.xy = 180;
            } else {
                var1.xy = 0;
            }

            int var16;
            for(var16 = var1.zy; var16 > 90; var16 -= 180) {
                ;
            }

            while(var16 < -90) {
                var16 += 180;
            }

            if (var16 > 0) {
                var1.zy += -1;
                this.smoke = true;
            }

            if (var16 < 0) {
                ++var1.zy;
                this.smoke = true;
            }

            if (this.speed > 10.0F && this.u.down) {
                var1.zy += (int)(5.0F * var1.m.cs.getcos(var1.xy));
            }
        }

        if (this.u.left) {
            if (var1.y < 207) {
                if (var1.xy > -90) {
                    var1.xy -= 10;
                }
            } else {
                var1.xz += 2;
            }
        }

        if (this.u.right) {
            if (var1.y < 207) {
                if (var1.xy < 90) {
                    var1.xy += 10;
                }
            } else {
                var1.xz -= 2;
            }
        }

        int var20 = (int)((float)(var8 * 4) * var1.m.cs.getsin(var1.xy));
        var1.xz -= var20;
        if (var1.nhits > var1.maxhits - var1.maxhits / 6 && !var1.exp) {
            if (this.rspeed > 60) {
                this.rspeed = 60;
                this.speed = 60.0F;
            }

            var1.xz += (int)(Math.random() * (double)(this.speed / 10.0F) - (double)(this.speed / 20.0F));
            var1.zy += (int)(Math.random() * (double)(this.speed / 10.0F) - (double)(this.speed / 20.0F));
        }

        this.rlift = (int)(this.speed * var1.m.cs.getcos(var1.zy) * var1.m.cs.getcos(var1.xy)) - 40;
        if (this.lift < (double)this.rlift) {
            this.lift += 0.5D;
        }

        if (this.lift > (double)this.rlift) {
            this.lift -= 0.5D;
        }

        if (this.lift < (double)(-(50.0F - this.speed / 2.0F))) {
            this.lift = (double)(-(50.0F - this.speed / 2.0F));
        }

        int var21 = (int)(5.0F * var1.m.cs.getcos(var1.zy) * var1.m.cs.getcos(var1.xy));
        if (this.lift > (double)var21) {
            this.lift = (double)var21;
        }

        var1.y -= (int)this.lift;
        if (var1.x < -40000) {
            var1.x = -40000;
            if (var20 <= 0) {
                var1.xz += 5;
            } else {
                var1.xz -= 5;
            }
        }

        if (var1.x > 40000) {
            var1.x = 40000;
            if (var20 <= 0) {
                var1.xz += 5;
            } else {
                var1.xz -= 5;
            }
        }

        if (var1.z > 40000) {
            var1.z = 40000;
            if (var20 <= 0) {
                var1.xz += 5;
            } else {
                var1.xz -= 5;
            }
        }

        if (var1.z < -40000) {
            var1.z = -40000;
            if (var20 <= 0) {
                var1.xz += 5;
            } else {
                var1.xz -= 5;
            }
        }

        if (!this.pexp && var1.exp) {
            if (this.speed > 30.0F) {
                this.speed = -15.0F;
                this.pexp = true;
            } else if (var1.nhits > var1.maxhits) {
                this.pexp = true;
            } else {
                var1.exp = false;
                this.speed = -(((float)this.rspeed + this.speed) / 2.0F);
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
                this.speed = (float)((double)this.speed - 0.5D);
            }

            if (this.speed < (float)this.rspeed) {
                ++this.speed;
            }
        }

        if (this.u.fire && !var1.exp) {
            if (this.skip && this.bulkc < this.lsr.srate[this.ltyp]) {
                this.lx[this.nl] = var1.x;
                this.ly[this.nl] = var1.y;
                this.lz[this.nl] = var1.z;
                this.lxz[this.nl] = var1.xz;
                this.lzy[this.nl] = var1.zy;
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

        int var22 = 0;
        int var23 = 0;

        while(true) {
            if (this.lstage[var23] != 0) {
                ++var22;
                if (this.ly[var23] > 240 && this.lhit[var23] == 0) {
                    this.lhit[var23] = 1;
                }

                if (this.lhit[var23] == 0) {
                    if (this.lstage[var23] > 10 && this.nf[var23] < 15) {
                        int var28 = -1;
                        int var38 = -1;
                        if (!var2[var5].exp) {
                            var28 = this.getpy(var2[var5].x, var2[var5].y, var2[var5].z, var23);
                            var38 = var5;
                        }

                        for(int var44 = var6; var44 < var6 + 13; ++var44) {
                            int var47 = this.getpy(var2[var44].x, var2[var44].y, var2[var44].z, var23);
                            if (var47 < var28 && var47 > 0 && !var2[var44].exp) {
                                var28 = var47;
                                var38 = var44;
                            }
                        }

                        if (var28 < 22500 && var28 > 0) {
                            if (this.lspeed[var23] > 230) {
                                this.lspeed[var23] = 230;
                            }

                            int var45 = var2[var38].x;
                            int var48 = var2[var38].z;
                            int var17 = var2[var38].y;
                            short var18 = 0;
                            if (var45 - this.lx[var23] > 0) {
                                var18 = 180;
                            }

                            this.lxz[var23] = (int)((double)(90 + var18) + Math.atan((double)(var48 - this.lz[var23]) / (double)(var45 - this.lx[var23])) / 0.017453292519943295D);
                            var18 = 0;
                            if (var17 - this.ly[var23] < 0) {
                                var18 = -180;
                            }

                            int var19 = (int)Math.sqrt((double)((var48 - this.lz[var23]) * (var48 - this.lz[var23]) + (var45 - this.lx[var23]) * (var45 - this.lx[var23])));
                            this.lzy[var23] = -((int)((double)(90 + var18) - Math.atan((double)var19 / (double)(var17 - this.ly[var23])) / 0.017453292519943295D));
                            ++this.nf[var23];
                        }
                    }

                    this.lx[var23] -= (int)((float)this.lspeed[var23] * var1.m.cs.getsin(this.lxz[var23]) * var1.m.cs.getcos(this.lzy[var23]));
                    this.lz[var23] += (int)((float)this.lspeed[var23] * var1.m.cs.getcos(this.lxz[var23]) * var1.m.cs.getcos(this.lzy[var23]));
                    this.ly[var23] -= (int)((float)this.lspeed[var23] * var1.m.cs.getsin(this.lzy[var23]));
                    ++this.lstage[var23];
                    if (this.lstage[var23] > 80) {
                        this.lstage[var23] = 0;
                    }
                }
            }

            ++var23;
            if (var23 >= 20) {
                break;
            }
        }

        if (var22 != 0) {
            if (!var1.fire) {
                var1.fire = true;
            }
        } else if (var1.fire) {
            var1.fire = false;
        }

        var1.x -= (int)(this.speed * var1.m.cs.getsin(var1.xz) * var1.m.cs.getcos(var1.zy));
        var1.z += (int)(this.speed * var1.m.cs.getcos(var1.xz) * var1.m.cs.getcos(var1.zy));
        var1.y -= (int)(this.speed * var1.m.cs.getsin(var1.zy));
        if (var1.y > 215) {
            var1.y = 215;
        }

        if (var1.y < -25000) {
            var1.y = -25000;
        }

        if (this.tcnt > this.turnat) {
            if (this.targeting) {
                this.targeting = false;
            }

            if (this.mode != 1 && this.mode != 3) {
                if (this.engage) {
                    short var29 = 0;
                    if (var2[var6 + this.ens].x - var1.x > 0) {
                        var29 = 180;
                    }

                    this.gxz = (int)((double)(90 + var29) + Math.atan((double)(var2[var6 + this.ens].z - var1.z) / (double)(var2[var6 + this.ens].x - var1.x)) / 0.017453292519943295D);
                    var29 = 0;
                    if (var2[var6 + this.ens].y - var1.y < 0) {
                        var29 = -180;
                    }

                    int var39 = (int)Math.sqrt((double)((var2[var6 + this.ens].z - var1.z) * (var2[var6 + this.ens].z - var1.z) + (var2[var6 + this.ens].x - var1.x) * (var2[var6 + this.ens].x - var1.x)));
                    this.gzy = -((int)((double)(90 + var29) - Math.atan((double)var39 / (double)(var2[var6 + this.ens].y - var1.y)) / 0.017453292519943295D));
                    var23 = this.getcpy(var1, var2[var6 + this.ens]);
                    if (var23 > 0 && var23 < 15000) {
                        this.targeting = true;
                    }

                    if (var23 > 0 && var23 < 200 && Math.random() > 0.7D) {
                        if (Math.random() > 0.5D) {
                            this.enx = -6800 + (int)(2000.0D + 30000.0D * Math.random());
                        } else {
                            this.enx = -6800 - (int)(2000.0D + 30000.0D * Math.random());
                        }

                        if (Math.random() > 0.5D) {
                            this.enz = -1150 + (int)(2000.0D + 30000.0D * Math.random());
                        } else {
                            this.enz = -1150 - (int)(2000.0D + 30000.0D * Math.random());
                        }

                        if (Math.random() > 0.7D) {
                            this.eny = 0;
                        } else {
                            this.eny = -((int)(Math.random() * 23000.0D));
                        }

                        this.engage = false;
                        this.targeting = false;
                    }
                } else {
                    short var31 = 0;
                    if (this.enx - var1.x > 0) {
                        var31 = 180;
                    }

                    this.gxz = (int)((double)(90 + var31) + Math.atan((double)(this.enz - var1.z) / (double)(this.enx - var1.x)) / 0.017453292519943295D);
                    var31 = 0;
                    if (this.eny - var1.y < 0) {
                        var31 = -180;
                    }

                    int var40 = (int)Math.sqrt((double)((this.enz - var1.z) * (this.enz - var1.z) + (this.enx - var1.x) * (this.enx - var1.x)));
                    this.gzy = -((int)((double)(90 + var31) - Math.atan((double)var40 / (double)(this.eny - var1.y)) / 0.017453292519943295D));
                    var23 = this.getepy(var1);
                    if (var23 > 0 && var23 < 500) {
                        this.ens = 4 + (int)(Math.random() * 5.0D);
                        this.engage = true;
                    }
                }

                this.turnat = (int)(Math.random() * 50.0D);
            }

            var23 = this.getcpy(var2[var5], var1);
            if (var23 > 0) {
                if (var23 < 20000 && !var2[var5].exp) {
                    if (this.mode == 0 && this.mode != 3) {
                        if (Math.random() > 0.5D && var1.maxR != 151) {
                            this.mode = 2;
                        } else {
                            this.mode = 1;
                            this.m1cnt = 0;
                        }
                    }
                } else if (this.mode != 0) {
                    this.mode = 0;
                }
            }

            if (this.mode == 1) {
                short var33 = 0;
                if (var2[var5].x - var1.x > 0) {
                    var33 = 180;
                }

                this.gxz = (int)((double)(90 + var33) + Math.atan((double)(var2[var5].z - var1.z) / (double)(var2[var5].x - var1.x)) / 0.017453292519943295D);
                var33 = 0;
                if (var2[var5].y - var1.y < 0) {
                    var33 = -180;
                }

                int var41 = (int)Math.sqrt((double)((var2[var5].z - var1.z) * (var2[var5].z - var1.z) + (var2[var5].x - var1.x) * (var2[var5].x - var1.x)));
                this.gzy = -((int)((double)(90 + var33) - Math.atan((double)var41 / (double)(var2[var5].y - var1.y)) / 0.017453292519943295D));
                this.turnat = (int)(Math.random() * 3.0D);
                if (var23 < 7000) {
                    this.targeting = true;
                }

                ++this.m1cnt;
                if (this.m1cnt > this.relax) {
                    this.mode = 0;
                }
            }

            if (this.mode == 3) {
                short var35 = 0;
                if (var2[this.m3o].x - var1.x > 0) {
                    var35 = 180;
                }

                this.gxz = (int)((double)(90 + var35) + Math.atan((double)(var2[this.m3o].z - var1.z) / (double)(var2[this.m3o].x - var1.x)) / 0.017453292519943295D);
                var35 = 0;
                if (var2[this.m3o].y - var1.y < 0) {
                    var35 = -180;
                }

                int var42 = (int)Math.sqrt((double)((var2[this.m3o].z - var1.z) * (var2[this.m3o].z - var1.z) + (var2[this.m3o].x - var1.x) * (var2[this.m3o].x - var1.x)));
                this.gzy = -((int)((double)(90 + var35) - Math.atan((double)var42 / (double)(var2[this.m3o].y - var1.y)) / 0.017453292519943295D));
                this.turnat = (int)(Math.random() * 10.0D);
                ++this.m3cnt;
                if (this.m3cnt == this.runn) {
                    this.mode = 0;
                }
            }

            this.tcnt = 0;
        } else {
            ++this.tcnt;
        }

        if (this.mode != 3 && var1.hit && Math.random() > 0.85D) {
            if (Math.random() > 0.5D) {
                this.m3o = this.nearst(var2, var3, var4, var5, var1);
                this.mode = 3;
                this.m3cnt = 0;
            } else if (this.mode == 2) {
                if (var1.zy < 15 && Math.random() < 0.5D && var1.maxR != 151) {
                    this.turnat = 20;
                    this.gzy = 80;
                    this.mode = 0;
                } else {
                    this.mode = 1;
                    this.m1cnt = 0;
                }
            } else if (var1.zy < 15 && Math.random() < 0.5D) {
                this.turnat = 20;
                this.gzy = 80;
                this.mode = 0;
            } else {
                this.mode = 2;
            }
        }

        var23 = 0;
        if ((float)var1.y > 100.0F + (float)this.liftup * var1.m.cs.getsin(var1.zy)) {
            var23 = 1;
        }

        int var37 = var1.y + (int)((float)(-(var1.z + 1000 - var1.z)) * var1.m.cs.getsin(var1.zy));
        int var43 = var1.z + (int)((float)(var1.z + 1000 - var1.z) * var1.m.cs.getcos(var1.zy));
        int var46 = var1.x + (int)((float)(-(var43 - var1.z)) * var1.m.cs.getsin(var1.xz));
        int var49 = var1.z + (int)((float)(var43 - var1.z) * var1.m.cs.getcos(var1.xz));
        if (this.myway(var2, var3, var4, var5, var46, var37, var49)) {
            var23 = 2;
        }

        if (this.u.left) {
            this.u.left = false;
        }

        if (this.u.right) {
            this.u.right = false;
        }

        if (this.u.up) {
            this.u.up = false;
        }

        if (this.u.down) {
            this.u.down = false;
        }

        if (var23 != 2) {
            int var50;
            for(var50 = var1.xz; var50 >= 360; var50 -= 360) {
                ;
            }

            while(var50 < 0) {
                var50 += 360;
            }

            if (Math.abs(var50 - this.gxz) > 5 && var23 == 0) {
                if (var50 > 270 && this.gxz < 90) {
                    this.u.left = true;
                    this.trgxz = 360 - var50 + this.gxz;
                } else if (var50 < 90 && this.gxz > 270) {
                    this.u.right = true;
                    this.trgxz = 360 - this.gxz + var50;
                } else if (var50 < this.gxz) {
                    this.u.left = true;
                    this.trgxz = this.gxz - var50;
                } else {
                    this.u.right = true;
                    this.trgxz = var50 - this.gxz;
                }

                if (this.dracs && Math.abs(var1.xy) > 80) {
                    this.u.down = true;
                }
            } else {
                if (var1.xy > 0) {
                    this.u.left = true;
                }

                if (var1.xy < 0) {
                    this.u.right = true;
                }

                if (var23 == 1 && Math.abs(var1.xy) < 30 && var1.zy < -30) {
                    this.gzy = 20;
                }
            }

            if (Math.abs(var1.zy - this.gzy) > 5 && Math.abs(var1.xy) < 45) {
                if (this.gzy < var1.zy) {
                    this.u.up = true;
                }

                if (this.gzy > var1.zy) {
                    this.u.down = true;
                }

                this.trgzy = Math.abs(var1.zy - this.gzy);
            }
        } else if (Math.abs(var1.xy) >= 60 && var1.zy >= 10) {
            if (var1.xy > 0) {
                this.u.right = true;
            }

            if (var1.xy < 0) {
                this.u.left = true;
            }

            if (var1.zy < 80) {
                this.u.down = true;
            }

            this.tcnt = 0;
            this.turnat = (int)(Math.random() * 4.0D + 3.0D);
        } else {
            if (var1.xy > 0) {
                this.u.left = true;
            }

            if (var1.xy < 0) {
                this.u.right = true;
            }

            if (var1.zy < 80) {
                this.u.down = true;
                this.gzy = 80;
            }

            this.tcnt = 0;
            this.turnat = (int)(Math.random() * 6.0D + 4.0D);
        }

        if (this.trgxz < 90 && this.trgzy < 40 && this.targeting) {
            if (!this.u.fire) {
                this.u.fire = true;
            }
        } else if (this.u.fire) {
            this.u.fire = false;
        }

    }

    public void dosmokes(Graphics var1, ContO var2) {
        if (!var2.exp) {
            if (var2.nhits > var2.maxhits - var2.maxhits / 3) {
                if (this.dms[this.nd] == -1) {
                    this.dx[this.nd] = var2.x + (int)(Math.random() * 60.0D - 30.0D);
                    this.dy[this.nd] = var2.y;
                    this.dz[this.nd] = var2.z;
                    this.dxz[this.nd] = var2.xz;
                    this.dzy[this.nd] = var2.zy;
                    this.dms[this.nd] = 0;
                    ++this.nd;
                    if (this.nd == 4) {
                        this.nd = 0;
                    }
                }

                int var3 = 0;

                while(true) {
                    if (this.dms[var3] != -1) {
                        if (this.dms[var3] < 4) {
                            this.lsr.hsmoke(var1, this.dx[var3], this.dy[var3], this.dz[var3], this.dxz[var3], this.dzy[var3], this.dms[var3]);
                        }

                        this.dy[var3] -= 15;
                        ++this.dms[var3];
                        if (this.dms[var3] >= 7) {
                            this.dms[var3] = -1;
                        }
                    }

                    ++var3;
                    if (var3 >= 4) {
                        break;
                    }
                }
            }

            if (var2.y > 200) {
                if (this.smoke && this.sms[this.ns] == -1) {
                    this.sx[this.ns] = var2.x + (int)(Math.random() * 80.0D - 40.0D);
                    this.sy[this.ns] = var2.y + 15;
                    this.sz[this.ns] = var2.z;
                    this.sxz[this.ns] = var2.xz;
                    this.szy[this.ns] = var2.zy;
                    this.sms[this.ns] = 0;
                    ++this.ns;
                    if (this.ns == 4) {
                        this.ns = 0;
                    }

                    this.smoke = false;
                }

                int var4 = 0;

                while(true) {
                    if (this.sms[var4] != -1) {
                        if (this.sms[var4] < 4) {
                            this.lsr.gsmoke(var1, this.sx[var4], this.sy[var4], this.sz[var4], this.sxz[var4], this.szy[var4], this.sms[var4]);
                        }

                        this.sy[var4] -= 15;
                        ++this.sms[var4];
                        if (this.sms[var4] == 10) {
                            this.sms[var4] = -1;
                        }
                    }

                    ++var4;
                    if (var4 >= 4) {
                        break;
                    }
                }
            }
        }

    }

    public int nearst(ContO[] var1, int[] var2, int var3, int var4, ContO var5) {
        int var6 = this.getcpy(var1[var2[0]], var5);
        int var7 = var2[0];

        for(int var8 = 0; var8 < var3; ++var8) {
            if (var2[var8] != var4) {
                int var9 = this.getcpy(var1[var2[var8]], var5);
                if (var9 > 0 && var9 < var6 && !var1[var2[var8]].exp || var6 < 0) {
                    var6 = var9;
                    var7 = var2[var8];
                }
            }
        }

        return var7;
    }

    public void reset(int var1, int var2, int var3, int var4, int var5, int var6) {
        this.rspeed = var1;
        this.speed = (float)var1;
        this.rlift = 0;
        this.lift = 0.0D;
        this.pexp = false;
        this.ltyp = var2;
        this.mode = 0;
        this.relax = var3;
        this.runn = var4;
        this.liftup = var5;
        if (var6 == 1) {
            this.dracs = true;
        } else {
            this.dracs = false;
        }

        int var7 = 0;

        while(true) {
            this.lstage[var7] = 0;
            ++var7;
            if (var7 >= 20) {
                break;
            }
        }

    }

    public Craft(Medium var1) {
        this.lsr = new Lasers(var1);
        int var2 = 0;

        while(true) {
            this.sms[var2] = -1;
            ++var2;
            if (var2 >= 4) {
                break;
            }
        }

        var2 = 0;

        while(true) {
            this.dms[var2] = -1;
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

    public boolean myway(ContO[] var1, int[] var2, int var3, int var4, int var5, int var6, int var7) {
        int var8 = 0;

        for(int var9 = 0; var9 < var3; ++var9) {
            if (var2[var9] != var4) {
                int var10 = var1[var2[var9]].maxR / 20 * (var1[var2[var9]].maxR / 20);
                if (var10 < 5000) {
                    var10 = 5000;
                }

                var8 = (var1[var2[var9]].x - var5) / 10 * ((var1[var2[var9]].x - var5) / 10) + (var1[var2[var9]].y - var6) / 10 * ((var1[var2[var9]].y - var6) / 10) + (var1[var2[var9]].z - var7) / 10 * ((var1[var2[var9]].z - var7) / 10);
                if (var8 > 0 && var8 < var10 && !var1[var2[var9]].exp && var1[var2[var9]].maxR > 75) {
                    return true;
                }
            }
        }

        return false;
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

    public int getepy(ContO var1) {
        return (var1.x - this.enx) / 100 * ((var1.x - this.enx) / 100) + (var1.y - this.eny) / 100 * ((var1.y - this.eny) / 100) + (var1.z - this.enz) / 100 * ((var1.z - this.enz) / 100);
    }
}
