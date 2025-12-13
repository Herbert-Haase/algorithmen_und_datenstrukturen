#include "Sortieren.hpp"
#include <cstdlib>
#include <ctime>
#include <iostream>

#define N 1000

using namespace ex6;

class Selection : public Sortieren
{
public:
  using Sortieren::Sortieren;

  void sort() override
  {
    for (size_t i = 0; i < size - 1; i++)
    {
      int min = i;
      for (size_t j = i + 1; j < size; j++)
        if (get(j) < get(min))
          min = j;
      swap(i, min);
    }
  }
};

class Insertion : public Sortieren
{
public:
  using Sortieren::Sortieren;

  void sort() override
  {
    for (size_t i = 1; i < size; i++)
    {
      int x = a[i];
      int j = i - 1;

      while (j >= 0 && x < get(j))
      {
        a[j + 1] = a[j];
        j--;
      }
      a[j + 1] = x;
    }
  }
  void countAndReset() override
  {
    std::cout << "Anzahl Elementvergleiche: " << count << '\n';
    count = 0;
  }
};

class Quick : public Sortieren
{
public:
  using Sortieren::Sortieren;

  void sort() override
  {
    if (size > 0)
    {
      impSort(0, size - 1);
    }
  }
  void impSort(int left, int right)
  {
    if (right <= left)
      return;
    int i = partition(left, right);
    impSort(left, i - 1);
    impSort(i + 1, right);
  }
  int partition(int left, int right)
  {
    int i = left - 1, j = right;
    int pivot = a[right];
    while (true)
    {
      while (get(++i) < pivot)
        ;
      while (get(--j) > pivot)
        if (j == left)
          break;
      if (i >= j)
        break;
      swap(i, j);
    }
    swap(i, right);
    return i;
  }
  void countAndReset() override
  {
    std::cout << "Anzahl Elementvergleiche: " << count << '\n';
    count = 0;
  }
};

class Merge : public Sortieren
{
public:
  using Sortieren::Sortieren;

  void sort() override
  {
    if (size > 0)
    {
      impSort(0, size - 1);
    }
  }
  void impSort(int left, int right)
  {
    if (right <= left)
      return;
    int middle = (left + right) / 2;
    impSort(left, middle);
    impSort(middle + 1, right);
    merge(left, middle, right);
  }
  void merge(int li, int mi, int re)
  {
    int i, j, k;
    int *aux = new int[re + 1];
    for (i = mi + 1; i > li; i--)
      aux[i - 1] = a[i - 1];
    for (j = mi; j < re; j++)
      aux[re + mi - j] = a[j + 1];
    for (k = li; k <= re; k++)
    {
      if (getE(aux, j) < getE(aux, i))
        a[k] = aux[j--];
      else
        a[k] = aux[i++];
    }
    delete[] aux;
  }
  int getE(int *a, unsigned idx)
  {
    count++;
    return a[idx];
  }
};

int main()
{
  // std::array<int,4> unsorted{4,3,2,1};
  srand(time(nullptr));
  std::array<int, N> unsorted{};
  std::cout << "[";
  for (size_t i = 0; i < unsorted.size(); i++)
  {
    unsorted[i] = rand();
  std::cout << unsorted[i] << ", ";
  }
  std::cout << "]" << "\n\n";

  // selection
  Selection selection{unsorted};
  selection.sort();
  std::cout << "Selection: " /*<< selection */<< '\n';
  selection.countAndReset(); // 6
  // insertion
  Insertion insertion{unsorted};
  insertion.sort();
  std::cout << "Insertion: " << /*insertion << */'\n';
  insertion.countAndReset(); // 6
  // qsort
  Quick quick{unsorted};
  quick.sort();
  std::cout << "Quick: " << /*quick << */'\n';
  quick.countAndReset(); // 10
  // merge
  Merge merge{unsorted};
  merge.sort();
  std::cout << "Merge: " << /*merge << */'\n';
  merge.countAndReset(); // 8

  return 0;
}
