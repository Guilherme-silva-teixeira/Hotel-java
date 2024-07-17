class index
{
    public static void main(String[] args)
    {
        int A00[] = new int[4], A01[] = new int[4], A02[] = new int[4], A03[] = new int[4];
        int ad = 0;
        int[] suites = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        for (int i0 = 0 ; i0 < 4; i0++)
        {
            A00[i0] =  suites[ad];
            ad++;
        }
        for (int i1 = 0; i1 < 4; i1++)
        {
            A01[i1] = suites[ad];
            ad++;
        }
        for (int i2 = 0; i2 < 4; i2++)
        {
            A02[i2] = suites[ad];
            ad++;
        }
        for (int i3 = 0; i3 < 4; i3++)
        {
            A03[i3] = suites[ad];
            ad++;
        }
        for (int j = 0 ; j < 4 ; j++)
        {
            System.out.println(A00[j] +" "+ A01[j] +" "+ A02[j] +" "+ A03[j] );
        }
    }
}
