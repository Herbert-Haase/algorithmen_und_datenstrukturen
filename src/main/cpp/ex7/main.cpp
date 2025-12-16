#include <algorithm>
#include <cstdlib>
#include <iostream>
#include <memory>
#include <vector>

namespace ex7 {

struct Node {
  int key;
  int val;
  int height;
  Node *left;
  Node *right;
  Node *succ;
  Node *pred;

  Node(int key, int val)
      : key{key}, val{val}, height{0}, left{0}, right{0}, succ{0}, pred{0} {}
};

class AVLtree {
public:
  Node *root;
  AVLtree() : root{0} {}
  ~AVLtree() {
    if (root == nullptr)
      return;
    std::vector<Node *> stack{};
    stack.push_back(root);

    while (!stack.empty()) {
      Node *node = stack.back();

      if (node->right != nullptr) {
        stack.push_back(node->right);
        node->right = nullptr;
      } else if (node->left != nullptr) {
        stack.push_back(node->left);
        node->left = nullptr;
      } else {
        delete node;
        stack.pop_back();
      }
    }
  }
  AVLtree(const AVLtree &) = delete;
  AVLtree(const AVLtree &&) = delete;
  AVLtree &operator=(const AVLtree &) = delete;
  AVLtree &operator=(const AVLtree &&) = delete;

  Node *getMin(Node *p) const {
    if (p == nullptr)
      return p;
    while (p->pred != nullptr) {
      p = p->pred;
    }
    return p;
  }

  Node *getMax(Node *p) const {
    if (p == nullptr)
      return p;
    while (p->succ != nullptr) {
      p = p->succ;
    }
    return p;
  }

  int getHeight(const Node *p) const {
    if (p == nullptr)
      return -1;
    else
      return p->height;
  }
  int getBalance(const Node *p) const {
    if (p == nullptr)
      return 0;
    else
      return getHeight(p->right) - getHeight(p->left);
  }
  void rotateRight(Node *&p) {
    if (p->left != 0) {
      Node *q = p->left;
      p->left = q->right;
      q->right = p;
      p->height = std::max(getHeight(p->left), getHeight(p->right)) + 1;
      q->height = std::max(getHeight(q->left), getHeight(q->right)) + 1;
      p = q;
    }
  }
  void rotateLeft(Node *&p) {
    if (p->right != 0) {
      Node *q = p->right;
      p->right = q->left;
      q->left = p;
      p->height = std::max(getHeight(p->left), getHeight(p->right)) + 1;
      q->height = std::max(getHeight(q->left), getHeight(q->right)) + 1;
      p = q;
    }
  }
  void rotateLeftRight(Node *&p) {
    if (p == nullptr || p->left == nullptr)
      return;
    rotateLeft(p->left);
    rotateRight(p);
  }
  void rotateRightLeft(Node *&p) {
    if (p == nullptr || p->right == nullptr)
      return;
    rotateRight(p->right);
    rotateLeft(p);
  }
  void balance(Node *&p) {
    if (p == nullptr)
      return;

    p->height = std::max(getHeight(p->left), getHeight(p->right)) + 1;
    if (getBalance(p) == -2) // Baum ist linkslastig
    {
      if (getBalance(p->left) <= 0)
        rotateRight(p); // A1
      else
        rotateLeftRight(p);        // A2
    } else if (getBalance(p) == 2) // Baum ist linkslastig
    {
      if (getBalance(p->right) >= 0)
        rotateLeft(p); // B1
      else
        rotateRightLeft(p); // B2
    }
  }
  bool insert(int val) {
    if (root == nullptr) {
      root = new Node(val, val);
      return true;
    }
    return insert(val, val, root, root);
  }

  bool insert(int key, int val, Node *&par, Node *&p) {
    bool result;
    if (p == nullptr) {
      p = new Node(key, val);
      result = true;
      if (par->key < key) {
        p->pred = par;
        p->succ = par->succ;
        par->succ = p;
        if (p->succ)
          p->succ->pred = p;
      } else {
        p->succ = par;
        p->pred = par->pred;
        par->pred = p;
        if (p->pred)
          p->pred->succ = p;
      }
    } else if (key < p->key)
      result = insert(key, val, p, p->left);
    else if (key > p->key)
      result = insert(key, val, p, p->right);
    else
      result = false;
    if (result)
      balance(p);
    return result;
  }

  bool remove(int key, Node *&p) {
    if (p == 0)
      return false;
    if (key < p->key) {
      bool found = remove(key, p->left);
      if (found)
        balance(p);
      return found;
    } else if (key > p->key) {
      bool found = remove(key, p->right);
      if (found)
        balance(p);
      return found;
    } else if (p->left == 0 || p->right == 0) {
      Node *tmp = p;
      if (p->pred != 0)
        p->pred->succ = p->succ;
      if (p->succ != 0)
        p->succ->pred = p->pred;
      if (p->left != 0)
        p = p->left; // by-pass nach links
      else
        p = p->right; // by-pass nach rechts
      delete tmp;
      return true;
    } else {
      Node *min = p->succ;
      p->val = min->val;
      p->key = min->key;
      return remove(min->key, p->right);
    }
  }

  void PreOrder(Node *&p) {
    if (p != nullptr) {
      std::cout << p->val << ", ";
      PreOrder(p->left);
      PreOrder(p->right);
    }
  }
  void InOrder(Node *p) {
    while (p != 0) {
      std::cout << p->val << ", ";
      p = p->succ;
    }
  }
  void PostOrder(Node *&p) {
    if (p != nullptr) {
      PostOrder(p->left);
      PostOrder(p->right);
      std::cout << p->val << ", ";
    }
  }
  bool search(int key, int *value, Node *p) {
    if (p == 0)
      return false;
    else if (key < p->key)
      return search(key, value, p->left);
    else if (key > p->key)
      return search(key, value, p->right);
    else {
      *value = p->val;
      return true;
    }
  }
  void printTreeDebug() {
    Node *queue[100];
    int front = 0, back = 0;

    if (root == nullptr) {
      std::cout << "(empty)" << std::endl;
      return;
    }

    queue[back++] = root;

    std::cout << "Level-order: ";
    while (front < back) {
      Node *current = queue[front++];
      std::cout << current->val << " ";

      if (current->left)
        queue[back++] = current->left;
      if (current->right)
        queue[back++] = current->right;
    }
    std::cout << std::endl;

    // Also print the structure
    std::cout << "Structure:" << std::endl;
    std::cout << "  Node " << root->val << ": left="
              << (root->left ? std::to_string(root->left->val) : "null")
              << ", right="
              << (root->right ? std::to_string(root->right->val) : "null")
              << std::endl;
    if (root->left)
      std::cout << "  Node " << root->left->val << ": left="
                << (root->left->left ? std::to_string(root->left->left->val)
                                     : "null")
                << ", right="
                << (root->left->right ? std::to_string(root->left->right->val)
                                      : "null")
                << std::endl;
    if (root->right)
      std::cout << "  Node " << root->right->val << ": left="
                << (root->right->left ? std::to_string(root->right->left->val)
                                      : "null")
                << ", right="
                << (root->right->right ? std::to_string(root->right->right->val)
                                       : "null")
                << std::endl;
  }
};

} // namespace ex7

using namespace ex7;

int main(void) {
  AVLtree avl = AVLtree{};
  avl.insert(4);
  avl.insert(1);
  avl.insert(3);
  avl.insert(5);
  avl.insert(2);
  avl.insert(6);
  avl.remove(3, avl.root);
  avl.insert(5);
  avl.insert(2);
  avl.insert(6);
  std::cout << "PreOrder" << '\n';
  avl.PreOrder(avl.root);
  std::cout << '\n';
  std::cout << "InOrder" << '\n';
  avl.InOrder(avl.getMin(avl.root));
  std::cout << '\n';
  std::cout << "PostOrder" << '\n';
  avl.PostOrder(avl.root);
  std::cout << '\n';
  int six = 6;
  int three = 3;
  if (avl.search(6, &six, avl.root))
    std::cout << "6 was found" << '\n';
  if (!avl.search(3, &three, avl.root))
    std::cout << "3 was not found" << '\n';
  std::cout << '\n';
  avl.printTreeDebug();
  return 0;
}
