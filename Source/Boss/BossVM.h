#if !defined(BOSS_VM_H)
#define BOSS_VM_H

struct BossVM
{
  BossVM();
  ~BossVM();

  bool load( const char* filepath );
};

#endif // BOSS_H
