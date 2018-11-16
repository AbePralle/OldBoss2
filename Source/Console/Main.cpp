// Main.c
// Boss Console
#include <cstdio>
using namespace std;

#include "Boss.h"

int main( int argc, char* argv[] )
{
  BossVM vm;
  vm.load( "Test.boss" );
  return 0;
}
