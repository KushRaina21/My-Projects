from __future__ import print_function
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import classification_report
from sklearn import datasets
from skimage import exposure
import numpy as np
import math
import csv
import random
import operator
from PIL import Image
import sys
import traceback
from collections import Counter, defaultdict
import pylab as pl
import numpy as np

# import Opencv library
#import sudopy  # see: http://norvig.com/sudopy.shtml

import cv2

import math

'''----------------------------------------------------------------------Solve Sudoku Logic--------------------------------------------------'''
def next_Cell(grid, i, j):
    for x in range(i, 9):
        for y in range(j, 9):
            if grid[x][y] == 0:
                return x, y
    for x in range(0, 9):
        for y in range(0, 9):
            if grid[x][y] == 0:
                return x, y
    return -1, -1


def isValid(grid, i, j, e):
    rowOk = all([e != grid[i][x] for x in range(9)])
    if rowOk:
        columnOk = all([e != grid[x][j] for x in range(9)])
        if columnOk:
      
            secTopX, secTopY = 3 * (i // 3), 3 * (j // 3)  # floored quotient should be used here.
            for x in range(secTopX, secTopX + 3):
                for y in range(secTopY, secTopY + 3):
                    if grid[x][y] == e:
                        return False
            return True
    return False


def solveSudoku(grid, i=0, j=0):
    i, j = next_Cell(grid, i, j)
    if i == -1:
        return True
    for e in range(1, 10):
        if isValid(grid, i, j, e):
            grid[i][j] = e
            if solveSudoku(grid, i, j):
                return True
           
            grid[i][j] = 0
    return False


'''------------------------------------------------------------------loading training Dataset-------------------------------------------'''

def loadDataset(filename, trainingSet=[]):
    with open(filename) as csvfile:
        lines = csv.reader(csvfile, delimiter=',')

        dataset = list(lines)
        for x in range(len(dataset) - 1):
            trainingSet.append(dataset[x])
'''------------------------------------------------------------Image recognition-----------------------------------------------------'''


image_sudoku_original = cv2.imread('90d_rotate.jpg')

_ = pl.imshow(image_sudoku_original)
_ = pl.axis("off")


image_sudoku_gray = cv2.cvtColor(image_sudoku_original, cv2.COLOR_BGR2GRAY)

thresh = cv2.adaptiveThreshold(image_sudoku_gray, 255, 1, 1, 11, 15)


_ = pl.imshow(thresh, cmap=pl.gray())
_ = pl.axis("off")

name, contours0, hierarchy = cv2.findContours(thresh, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
h, w = image_sudoku_original.shape[:2]

image_sudoku_candidates = image_sudoku_original.copy()


'''------------------------------------------------Image Outline detection----------------------------------------------------------'''

# biggest rectangle
maximum_rectangle = 0;
for i in range(len(contours0)):
    
    approximation = cv2.approxPolyDP(contours0[i], 4, True)

    if (not (len(approximation) == 4)):
        continue;
   
    if (not cv2.isContourConvex(approximation)):
        continue;
       
    size_rectangle = cv2.contourArea(approximation)
   
    if size_rectangle > maximum_rectangle:
        maximum_rectangle = size_rectangle
        big_rectangle = approximation


approximation = big_rectangle
for i in range(len(approximation)):
    cv2.line(image_sudoku_candidates,
             (big_rectangle[(i % 4)][0][0], big_rectangle[(i % 4)][0][1]),
             (big_rectangle[((i + 1) % 4)][0][0], big_rectangle[((i + 1) % 4)][0][1]),
             (255, 0, 0), 2)

_ = pl.imshow(image_sudoku_candidates, cmap=pl.gray())
_ = pl.axis("off")


'''-------------------------------------------------------Rotate Image----------------------------------------------------------'''


def trans(sudoku):
    sudoku_1 = []
    for x in range(len(sudoku)):
        #print(x)
        new_matrix = []
        matrix = []
        matrix = sudoku[x]

        for x in range(len(matrix)):
            if matrix[x] == '255':
                matrix[x] = '1'

        new_matrix.append(matrix[:16])
        new_matrix.append(matrix[16:32])
        new_matrix.append(matrix[32:48])
        new_matrix.append(matrix[48:64])
        new_matrix.append(matrix[64:80])
        new_matrix.append(matrix[80:96])
        new_matrix.append(matrix[96:112])
        new_matrix.append(matrix[112:128])
        new_matrix.append(matrix[128:144])
        new_matrix.append(matrix[144:160])
        new_matrix.append(matrix[160:176])
        new_matrix.append(matrix[176:192])
        new_matrix.append(matrix[192:208])
        new_matrix.append(matrix[208:224])
        new_matrix.append(matrix[224:240])
        new_matrix.append(matrix[240:256])

       
        '''  
        for x in range(len(new_matrix)):
            for y in range(len(new_matrix[1])):
                new_matrix[x][y]=new_matrix[y][x]
        '''

        new_matrix = np.array(new_matrix)
        new_matrix = new_matrix.transpose()
       

        for x in range(len(new_matrix)):
            for y in range(len(new_matrix[1]) // 2):
                temp = new_matrix[x][(len(new_matrix[1])) - 1 - y]
                new_matrix[x][(len(new_matrix[1])) - 1 - y] = new_matrix[x][y]
                new_matrix[x][y] = temp

       

        list_1 = []
        for x in range(len(new_matrix)):
            for y in range(len(new_matrix)):
                list_1.append(new_matrix[x][y])

        sudoku_1.append(list_1)


    return (sudoku_1)


'''------------------------------------------------------Boundary(Edge) Detection-----------------------------------------------------------'''


IMAGE_WIDHT = 16
IMAGE_HEIGHT = 16
SUDOKU_SIZE = 9
N_MIN_ACTVE_PIXELS = 10


def edge_Points(rcCorners):
    ar = [];
    ar.append(rcCorners[0, 0, :]);
    ar.append(rcCorners[1, 0, :]);
    ar.append(rcCorners[2, 0, :]);
    ar.append(rcCorners[3, 0, :]);

    x_sum = sum(rcCorners[x, 0, 0] for x in range(len(rcCorners))) / len(rcCorners)
    y_sum = sum(rcCorners[x, 0, 1] for x in range(len(rcCorners))) / len(rcCorners)

    def algo(v):
        return (math.atan2(v[0] - x_sum, v[1] - y_sum)
                + 2 * math.pi) % 2 * math.pi
        ar.sort(key=algo)

    return (ar[3], ar[0], ar[1], ar[2])



points1 = np.array([
    np.array([0.0, 0.0], np.float32) + np.array([144, 0], np.float32),
    np.array([0.0, 0.0], np.float32),
    np.array([0.0, 0.0], np.float32) + np.array([0.0, 144], np.float32),
    np.array([0.0, 0.0], np.float32) + np.array([144, 144], np.float32),
], np.float32)
outerPoints = edge_Points(approximation)
points2 = np.array(outerPoints, np.float32)

pers = cv2.getPerspectiveTransform(points2, points1);

warp = cv2.warpPerspective(image_sudoku_original, pers, (SUDOKU_SIZE * IMAGE_HEIGHT, SUDOKU_SIZE * IMAGE_WIDHT));

warp_gray = cv2.cvtColor(warp, cv2.COLOR_BGR2GRAY)


_ = pl.imshow(warp_gray, cmap=pl.gray())
_ = pl.axis("off")



def find_biggest_bounding_box(im_number_thresh):
    jab, contour, hierarchy = cv2.findContours(im_number_thresh.copy(),
                                               cv2.RETR_CCOMP,
                                               cv2.CHAIN_APPROX_SIMPLE)

    biggest_bound_rect = [];
    bound_rect_max_size = 0;
    for i in range(len(contour)):
        bound_rect = cv2.boundingRect(contour[i])
        size_bound_rect = bound_rect[2] * bound_rect[3]
        if size_bound_rect > bound_rect_max_size:
            bound_rect_max_size = size_bound_rect
            biggest_bound_rect = bound_rect
    
    
    x_b, y_b, w, h = biggest_bound_rect;
    x_b = x_b - 1;
    y_b = y_b - 1;
    w = w + 2;
    h = h + 2;

    return [x_b, y_b, w, h]


def number_extraction(x, y):
 
    im_number = warp_gray[x * IMAGE_HEIGHT:(x + 1) * IMAGE_HEIGHT][:, y * IMAGE_WIDHT:(y + 1) * IMAGE_WIDHT]

    im_number_thresh = cv2.adaptiveThreshold(im_number, 255, 1, 1, 15, 9)

    for i in range(im_number.shape[0]):
        for j in range(im_number.shape[1]):
            dist_center = math.sqrt((IMAGE_WIDHT / 2 - i) ** 2 + (IMAGE_HEIGHT / 2 - j) ** 2);
            if dist_center > 6:
                im_number_thresh[i, j] = 0;

    n_active_pixels = cv2.countNonZero(im_number_thresh)
    return [im_number, im_number_thresh, n_active_pixels]


'''--------------------------------------------Extract Individual Sudoku cells (numbers)------------------------------------------------'''



sudoku = np.zeros(shape=(9 * 9, IMAGE_WIDHT * IMAGE_HEIGHT))


def Recognize_number(x, y):
   
    [im_number, im_number_thresh, n_active_pixels] = number_extraction(x, y)

    if n_active_pixels > N_MIN_ACTVE_PIXELS:
        [x_b, y_b, w, h] = find_biggest_bounding_box(im_number_thresh)

        im_t = cv2.adaptiveThreshold(im_number, 255, 1, 1, 15, 9);
        number = im_t[y_b:y_b + h, x_b:x_b + w]

        if number.shape[0] * number.shape[1] > 0:
            number = cv2.resize(number, (IMAGE_WIDHT, IMAGE_HEIGHT), interpolation=cv2.INTER_LINEAR)
            ret, number2 = cv2.threshold(number, 127, 255, 0)
            number = number2.reshape(1, IMAGE_WIDHT * IMAGE_HEIGHT)
            sudoku[x * 9 + y, :] = number;
            return 1

        else:
            sudoku[x * 9 + y, :] = np.zeros(shape=(1, IMAGE_WIDHT * IMAGE_HEIGHT));
            return 0


index_subplot = 0
n_numbers = 0
indexes_numbers = []
for i in range(SUDOKU_SIZE):
    for j in range(SUDOKU_SIZE):
        if Recognize_number(i, j) == 1:
            if (n_numbers % 5) == 0:
                index_subplot = index_subplot + 1
            indexes_numbers.insert(n_numbers, i * 9 + j)
            n_numbers = n_numbers + 1

f, axarr = pl.subplots(index_subplot, 5)

width = 0;


trainingSet = []
loadDataset('new_testdata.txt', trainingSet)
a = np.array(trainingSet)
X_Train, Y_Train = a[:, :256], a[:, 256]

model = KNeighborsClassifier(n_neighbors=1)
model.fit(X_Train, Y_Train)

for x in range(len(sudoku)):
    for y in range(len(sudoku[0])):
        if sudoku[x][y] == 255:
            sudoku[x][y] = 1

sudoku1 = trans(sudoku)

predictions = model.predict(sudoku1)
print("The predicted Grid from the input Image:")
print(predictions)
s_pred=str(predictions)
fs_pred=s_pred.replace(" ",",").replace("[","").replace("]","").replace("\n","").replace("'","")
fs_pred=fs_pred.replace('\n','')
list_pred=fs_pred.split(",")
grid = [list_pred[x:x+9] for x in range(0, len(list_pred), 9)]
grid=np.array(grid)
grid=grid.astype(int)
solveSudoku(grid)
print("\n\nCompletely Solved Grid")
print(grid)