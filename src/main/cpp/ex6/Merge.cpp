#include "Merge.hpp"

namespace ex6
{
void Merge::sort()
{
  if (size > 0)
  {
    impSort(0, size - 1);
  }
}

void Merge::impSort(int left, int right)
{
  if (right <= left)
    return;
  int middle = (left + right) / 2;
  impSort(left, middle);
  impSort(middle + 1, right);
  merge(left, middle, right);
}

void Merge::merge(int li, int mi, int re)
{
  int i, j, k;
  int *aux = new int[re + 1];

  for (i = mi + 1; i > li; i--)
    aux[i - 1] = a[i - 1];
  for (j = mi; j < re; j++)
    aux[re + mi - j] = a[j + 1];

  for (k = li; k <= re; k++)
  {
    if (less(aux[j], aux[i]))
      a[k] = aux[j--];
    else
      a[k] = aux[i++];
  }
  delete[] aux;
}
} // namespace ex6
