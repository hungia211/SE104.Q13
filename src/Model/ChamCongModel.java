package Model;

public class ChamCongModel {

    private int MaCC;
    private int MaNV;
    private int SGLT;
    private int SNDL;

    public ChamCongModel() {
    }

    public ChamCongModel(int MaNV, int SGLT, int SNDL) {
        this.MaNV = MaNV;
        this.SGLT = SGLT;
        this.SNDL = SNDL;
    }

    
    public ChamCongModel(int MaCC, int MaNV, int SGLT, int SNDL) {
        this.MaCC = MaCC;
        this.MaNV = MaNV;
        this.SGLT = SGLT;
        this.SNDL = SNDL;
    }

    
    public ChamCongModel(int SGLT) {
        this.SGLT = SGLT;
    }

//    public ChamCongModel(int maCC) {
//        this.MaCC = maCC;
//    }

    public int getmaCC() {
        return MaCC;
    }

    public void setmaCC(int maCC) {
        this.MaCC = maCC;
    }

    public int getmaNV() {
        return MaNV;
    }

    public void setmaNV(int maNV) {
        this.MaNV = maNV;
    }

    public int getSGLT() {
        return SGLT;
    }

    public void setSGLT(int SGLT) {
        this.SGLT = SGLT;
    }

    public int getSNDL() {
        return SNDL;
    }

    public void setSNDL(int SNDL) {
        this.SNDL = SNDL;
    }
}
