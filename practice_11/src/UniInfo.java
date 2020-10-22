
public class UniInfo {
    int specialtyCode;
    String uni;

    public int getSpecialtyCode() {
        return specialtyCode;
    }

    public String getUni() {
        return uni;
    }

    public UniInfo(int specialtyCode, String uni) {
        this.specialtyCode = specialtyCode;
        this.uni = uni;
    }

    @Override
    public int hashCode() {
        return specialtyCode;
    }

    @Override
    public String toString() {
        return "{ "+" Specialty code: "+ specialtyCode +"; University: "+ uni +"} ";
    }
}
