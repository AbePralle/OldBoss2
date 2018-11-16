#include "Boss.h"

#include <cstdio>
using namespace std;

BossVM::BossVM()
{
  printf( "+BossVM\n" );
}

BossVM::~BossVM()
{
  printf( "-BossVM\n" );
}

bool BossVM::load( const char* filepath )
{
  BossScanner scanner;
  return false;
}

