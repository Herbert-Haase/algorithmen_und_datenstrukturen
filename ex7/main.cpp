#ifndef AVLtree_H
#define AVLtree_H

#include <cstdlib>
#include <iostream>
#include <memory>
#include <span>

namespace ex7
{

class AVLtree
{
private:
  struct Node
  {
    int height;
    int key;
    int val;
    Node *left;
    Node *right;
    Node *next;
  };

  Node *root;

public:
  AVLtree();
  ~AVLtree();
  AVLtree(const AVLtree &) = delete;
  AVLtree(const AVLtree &&) = delete;
  AVLtree &operator=(const AVLtree &) = delete;
  AVLtree &operator=(const AVLtree &&) = delete;

  bool insert(int key, int value, Node *&p)
  {
    bool result;
    if (p == 0)
    {
      p = new Node()
    }
  }
  void PreOrder(Node *&p)
  {
    if (p != 0)
    {
      std::cout << p->val << ", ";
      PreOrder(p->left);
      PreOrder(p->right);
    }
  }
  void InOrder(Node *&p)
  {
    // if (p != 0)
    // {
    //   InOrder(p->left);
    //   std::cout << p->val << ", ";
    //   InOrder(p->right);
    // }
    Node *current = p;
    while (current != 0)
    {
      std::cout << current->val << ", ";
      current = current->next;
    }
  }
  void PostOrder(Node *&p)
  {
    if (p != 0)
    {
      PostOrder(p->left);
      PostOrder(p->right);
      std::cout << p->val << ", ";
    }
  }
  bool search(int key, int &value, Node *p)
  {
    if (p == 0)
      return false;
    else if (key < p->key)
      return search(key, value, p->left);
    else if (key > p->key)
      return search(key, value, p->right);
    else
    {
      value = p->val;
      return true;
    }
  }
  bool remove(int key, Node *&p);
};

} // namespace ex7

#endif
