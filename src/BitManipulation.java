public class BitManipulation {
         
   public static void main(String[] args) {
      int a = 12;
      int b = 7;
      // a = 1100 (12), b = 0111 (7). a AND b = 0100 (4)
      System.out.println("a AND b = " + ( a & b ) );
      
      b = 2;
      // a = 1100 (12), b = 0010 (2). a OR b = 1110 (14)
      System.out.println("a OR b = " + ( a | b ) );
      
      a = 8;
      b = 11;
      // a = 1000 (8), b = 1011 (11). a XOR b = 0011 (3)
      System.out.println("a XOR b = " + ( a ^ b ) );
      
      int val = 5;
      // val = 0101 (5). one left shift = 01010 (10), two left shifts = 010100 (20)
      System.out.println("val left shifted twice = " + ( val << 2 ) );
      
      val = 17;
      // val = 10001 (17). one right shift = 01000 (8), two right shifts = 00100 (4)
      System.out.println("val right shifted twice = " + ( val >> 2 ) );
      
      // packing and unpacking
      int left16 = 73; // 73 in 16-bit binary is 00000000 01001001
      int right16 = 22; // 22 in 16-bit binary is 00000000 00010110
      int num32 = 0; // we want to pack left16 and right16 into this variable
      
      // to pack, we left shift left16 by 16 places, then OR with right16
      num32 = ( left16 << 16 ) | right16;
      // num32 = 00000000 01001001 00000000 00010110 (4784150)
      System.out.println("Packed value = " + num32);
      
      // clear out left16 and right 16
      left16 = 0;
      right16 = 0;
      
      // to unpack right16, we mask out left16 and keep right16, using 0x0000FFFF
      // 0x0000FFFF is 00000000 00000000 11111111 11111111 in binary
      // Since "leading zeroes" are assumed, just AND with 0xFFFF
      // Masking causes right16 to be 00000000 00010110 (22)
      right16 = num32 & 0xFFFF;
      System.out.println("right16 = " + right16);
      
      // to unpack left16, we right shift 16 times
      // masking is not actually needed since we shifted out right16 completely
      // right shifting caused left16 to be 00000000 01001001 (73)
      left16 = ( num32 >> 16 ) & 0xFFFF;
      System.out.println("left16 = " + left16);
   }
}
