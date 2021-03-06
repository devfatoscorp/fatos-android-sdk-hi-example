package kr.fatos.tnavi.Unit;

public class RouteType
{
    public static final int ROUTE_RECOMM = 1; // 추천1 (recommend)
    public static final int ROUTE_OPTION2 = 2; // 추천2 (not used)
    public static final int ROUTE_EXP = 4; // 고속도로 우선 (exp)
    public static final int ROUTE_GENERAL = 8; // 일반도로 우선 (general)
    public static final int ROUTE_Short = 16; // 최단거리 (short)
    public static final int ROUTE_Recommend_Free = 32; // 무료도로 (free)
    public static final int ROUTE_Short_Free = 256; //최단거리 무료
}
