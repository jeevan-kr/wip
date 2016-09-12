import numpy as np
#setup a standard image size; this will distort some images but will get everything into the same shape
from PIL import Image
#np.set_printoptions(threshold=np.nan)

STANDARD_SIZE = (300*3, 167*3)
def img_to_matrix(filename, verbose=False):
    """
    takes a filename and turns it into a numpy array of RGB pixels
    """
    img = Image.open(filename)
    print "changing size from %s to %s" % (str(img.size), str(STANDARD_SIZE))
    #img = img.resize(STANDARD_SIZE)
    img = list(img.getdata())
    img = map(list, img)
    img = np.array(img)
    return img
 
def flatten_image(img):
    """
    takes in an (m, n) numpy array and flattens it 
    into an array of shape (1, m * n)
    """
    s = img.shape[0] * img.shape[1]
    img_wide = img.reshape(1, s)
    return img_wide[0]


