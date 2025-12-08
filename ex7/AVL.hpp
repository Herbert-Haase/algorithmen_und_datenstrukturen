#ifndef AVLtree_H
#define AVLtree_H

#include <cstdlib>
#include <iostream>
#include <memory>
#include <span>

namespace ex7 {

class AVLtree {
public:
  AVLtree(std::span<int> arr);
  ~AVLtree();
  AVLtree(const AVLtree&);
  AVLtree(const AVLtree&&);
  AVLtree &operator=(const AVLtree&);
  AVLtree &operator=(const AVLtree&&);

  bool insert(int key, int value, Node*& node) {
    bool result;
    if (p == 0){
      p = new Node()
    }
  }
  void PreOrder(Node*& node);
  void InOrder(Node*& node);
  void PostOrder(Node* &node);
  bool search(int key,int value,Node*& node);
  bool remove(int key,Node*& node);



private:
  Node* head;
};

class Node{
  int height;
  int key;
  int val;
  Node* left;
  Node* right;
  Node* next;
};

} //namespace ex7

#endif
