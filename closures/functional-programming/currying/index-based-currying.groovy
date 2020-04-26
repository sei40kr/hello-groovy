def volume = { double l, double w, double h -> l * w * h }
def fixedWidthVolume = volume.ncurry(1, 2d)
assert volume(3d, 2d, 4d) == fixedWidthVolume(3d, 4d)
def fixedWidthAndHeight = volume.ncurry(1, 2d, 4d)
assert volume(3d, 2d, 4d) == fixedWidthAndHeight(3d)
