#include <stdio.h>

float ke_plusF(float a, float b) {
    return a + b;
}

int ke_plusI(int a, int b) {
    return a + b;
}

int main(void) {
    printf("Hello!\n");
    
    int r = ke_plusI(100, 200);
    printf("r = %d\n", r);
    
    float r2 = ke_plusF(100.5f, 200.2f);
    printf("r2 = %f\n", r2);
} 






