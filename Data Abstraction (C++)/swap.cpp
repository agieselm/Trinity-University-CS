template<typename T>

void swap (T& x, T& y) {

   T temp(std::move(x));
      x = std::move (y);
      y = move(temp);

}
