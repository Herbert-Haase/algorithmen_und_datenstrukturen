#include "Sortieren.hpp"
#include <algorithm>
#include <iostream>
#include <memory>
#include <span>

namespace ex6
{

Sortieren::Sortieren(std::span<int> arr)
    : count{0}, a{std::make_unique<int[]>(arr.size())}, size{arr.size()}
{
  std::copy(arr.begin(), arr.end(), a.get());
}
void Sortieren::countAndReset()
{
  std::cout << "Anzahl Elementvergleiche: " << count / 2 << '\n';
  count = 0;
}

int Sortieren::get(unsigned idx)
{
  count++;
  return a[idx];
}

void Sortieren::swap(int i, int j)
{
  int t = a[i];
  a[i] = a[j];
  a[j] = t;
}

int &Sortieren::operator[](int index) { return a[index]; }

std::ostream &operator<<(std::ostream &os, const ex6::Sortieren &s)
{
  os << "[";
  for (size_t i = 0; i < s.size; ++i)
  {
    os << s.a[i];
    if (i < s.size - 1)
      os << ", ";
  }
  os << "]";
  return os;
}

} // namespace ex6
