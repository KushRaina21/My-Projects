# Raina , Kush 
# 1001-567-809
# 2018-11-16
# Assignment-05-01
import sys
import random
import time
import os
import scipy.misc
import pandas as pd
from sklearn.preprocessing import MinMaxScaler
from sklearn.model_selection import train_test_split
from numpy.linalg import inv

import tensorflow as tf



if sys.version_info[0] < 3:
    import Tkinter as tk
else:
    import tkinter as tk
from tkinter import simpledialog
from tkinter import filedialog
import matplotlib
from mpl_toolkits.mplot3d import Axes3D

matplotlib.use('TkAgg')
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
from matplotlib.backends.backend_agg import FigureCanvasAgg
import matplotlib.pyplot as plt
import numpy as np
import matplotlib as mpl
from matplotlib.ticker import LinearLocator, FormatStrFormatter
import matplotlib.backends.tkagg as tkagg
from matplotlib.colors import LinearSegmentedColormap,ListedColormap


import sklearn.datasets
from sklearn.cluster import AgglomerativeClustering

class MainWindow(tk.Tk):


    """
    This class creates and controls the main window frames and widgets
    Kush Raina 2018_06_03
    """
   
        # set the properties of the row and columns in the master frame
        # self.master_frame.rowconfigure(0, weight=1,uniform='xx')
        # self.master_frame.rowconfigure(1, weight=1, uniform='xx')
        # self.master_frame.rowconfigure(1, weight=20, minsize=400, uniform='xx')
    def generate_Random_Weights(self,file_name):
        self.weight_Matrix=np.random.uniform(-0.001,0.001,7840)
        
        self.weight_Matrix=np.reshape(self.weight_Matrix,(10,784))
         # reshape to column vector and return it 

       
    def read_one_image_and_convert_to_vector(self,file_name):
        img = scipy.misc.imread(file_name).astype(np.float32) # read image and convert to float
        #print(img.reshape(-1,1))
        return img.reshape(-1,1) # reshape to column vector and return it

    def convert_images_toVectors(self,file_name):
        img = scipy.misc.imread(file_name).astype(np.float32) # read image and convert to float
        #print(img.reshape(-1,1))
        return img.reshape(-1,1) # reshape to column vector and return it
  
    
    def normalize(self,img):
        img=np.true_divide(img, 127.5) # read image and convert to float
        img=np.subtract(img,1)
        #print(img)
        return img # reshape to column vector and return it 
    
    

        #print(img)
        

    def __init__(self, debug_print_flag=False):
        tk.Tk.__init__(self)
#        files=os.listdir("Data")
#        self.flag=True
#        
#        self.weight_Matrix=np.random.uniform(-0.001,0.001,7840)
#        
#        self.weight_Matrix=np.reshape(self.weight_Matrix,(10,784))
#        for file in files:
#            print("file ",file)
#            
#            img=self.read_one_image_and_convert_to_vector("Data/"+file)
#            img=self.normalize(img)
#            label=float(file[0])
#            img=np.row_stack((img,label))
#            print(img)
#         
#            #img=np.transpose(img)
#            if self.flag:
#                final=np.copy(img)
#                self.flag=False
#            else:
#                final=np.column_stack((final,img))
#                print(final)
#        
#        
#        final=final.transpose()   
#        np.random.shuffle(final)
#        train = final[:800, :]
#        test=final[800:, :]
        
        self.debug_print_flag = debug_print_flag
        self.master_frame = tk.Frame(self)
        self.master_frame.grid(row=0, column=0, sticky=tk.N + tk.E + tk.S + tk.W)
        self.rowconfigure(0, weight=1, minsize=500)
        self.columnconfigure(0, weight=1, minsize=500)
        
        # set the properties of the row and columns in the master frame
        # self.master_frame.rowconfigure(0, weight=1,uniform='xx')
        # self.master_frame.rowconfigure(1, weight=1, uniform='xx')
        # self.master_frame.rowconfigure(1, weight=20, minsize=400, uniform='xx')
        self.master_frame.rowconfigure(2, weight=1, minsize=10, uniform='xx')
        self.master_frame.columnconfigure(0, weight=1, minsize=200, uniform='xx')
        #self.master_frame.columnconfigure(1, weight=1, minsize=200, uniform='xx')
        # create all the widgetsself.menu_bar=null; 
        self.menu_bar = MenuBar(self, self.master_frame, background='red')
        self.tool_bar = ToolBar(self, self.master_frame)
        self.left_frame = tk.Frame(self.master_frame)
        self.right_frame = tk.Frame(self.master_frame)
        self.status_bar = StatusBar(self, self.master_frame, bd=1, relief=tk.SUNKEN)
        # Arrange the widgets
        #self.menu_bar.grid(row=0, columnspan=1, sticky=tk.N + tk.E + tk.S + tk.W)
        self.tool_bar.grid(row=1, columnspan=1, sticky=tk.N + tk.E + tk.S + tk.W)
        self.left_frame.grid(row=2, column=0, sticky=tk.N + tk.E + tk.S + tk.W)
        #self.right_frame.grid(row=2, column=1, sticky=tk.N + tk.E + tk.S + tk.W)
        self.status_bar.grid(row=3, columnspan=1, sticky=tk.N + tk.E + tk.S + tk.W)
        # Create an object for plotting graphs in the left frame
        self.display_activation_functions = LeftFrame(self, self.left_frame, debug_print_flag=self.debug_print_flag)
        # Create an object for displaying graphics in the right frame
        #self.display_graphics = RightFrame(self, self.right_frame, debug_print_flag=self.debug_print_flag)


class MenuBar(tk.Frame):
    def __init__(self, root, master, *args, **kwargs):
        tk.Frame.__init__(self, master, *args, **kwargs)
        self.root = root
        self.menu = tk.Menu(self.root)
        root.config(menu=self.menu)
        #self.file_menu = tk.Menu(self.menu)
        #self.menu.add_cascade(label="File", menu=self.file_menu)
        #self.file_menu.add_command(label="New", command=self.menu_callback)
        #self.file_menu.add_command(label="Open...", command=self.menu_callback)
        #self.file_menu.add_separator()
        #self.file_menu.add_command(label="Exit", command=self.menu_callback)
        #self.dummy_menu = tk.Menu(self.menu)
        #self.menu.add_cascade(label="Dummy", menu=self.dummy_menu)
        #self.dummy_menu.add_command(label="Item1", command=self.menu_item1_callback)
        #self.dummy_menu.add_command(label="Item2", command=self.menu_item2_callback)
        #self.help_menu = tk.Menu(self.menu)
        #self.menu.add_cascade(label="Help", menu=self.help_menu)
        #self.help_menu.add_command(label="About...", command=self.menu_help_callback)

    def menu_callback(self):
        self.root.status_bar.set('%s', "called the menu callback!")

    def menu_help_callback(self):
        self.root.status_bar.set('%s', "called the help menu callback!")

    def menu_item1_callback(self):
        self.root.status_bar.set('%s', "called item1 callback!")

    def menu_item2_callback(self):
        self.root.status_bar.set('%s', "called item2 callback!")


class ToolBar(tk.Frame):
    def __init__(self, root, master, *args, **kwargs):
        tk.Frame.__init__(self, master, *args, **kwargs)
        #self.root = root
        #self.master = master
        #self.var_filename = tk.StringVar()
        #self.var_filename.set('')
        #self.ask_for_string = tk.Button(self, text="Ask for a string", command=self.ask_for_string)
        #self.ask_for_string.grid(row=0, column=1)
        #self.file_dialog_button = tk.Button(self, text="Open File Dialog", fg="blue", command=self.browse_file)
        #self.file_dialog_button.grid(row=0, column=2)
        #self.open_dialog_button = tk.Button(self, text="Open Dialog", fg="blue", command=self.open_dialog_callback)
        #self.open_dialog_button.grid(row=0, column=3)

    def say_hi(self):
        self.root.status_bar.set('%s', "hi there, everyone!")

    def ask_for_string(self):
        s = simpledialog.askstring('My Dialog', 'Please enter a string')
        self.root.status_bar.set('%s', s)

    def ask_for_float(self):
        f = float(simpledialog.askfloat('My Dialog', 'Please enter a float'))
        self.root.status_bar.set('%s', str(f))

    def browse_file(self):
        self.var_filename.set(tk.filedialog.askopenfilename(filetypes=[("allfiles", "*"), ("pythonfiles", "*.txt")]))
        filename = self.var_filename.get()
        self.root.status_bar.set('%s', filename)

    def open_dialog_callback(self):
        d = MyDialog(self.root)
        self.root.status_bar.set('%s', "mydialog_callback pressed. Returned results: " + str(d.result))

    def button2_callback(self):
        self.root.status_bar.set('%s', 'button2 pressed.')

    def toolbar_draw_callback(self):
        self.root.display_graphics.create_graphic_objects()
        self.root.status_bar.set('%s', "called the draw callback!")

    def toolbar_callback(self):
        self.root.status_bar.set('%s', "called the toolbar callback!")


class MyDialog(tk.simpledialog.Dialog):
    def body(self, parent):
        tk.Label(parent, text="Integer:").grid(row=0, sticky=tk.W)
        tk.Label(parent, text="Float:").grid(row=1, column=0, sticky=tk.W)
        tk.Label(parent, text="String:").grid(row=1, column=2, sticky=tk.W)
        self.e1 = tk.Entry(parent)
        self.e1.insert(0, 0)
        self.e2 = tk.Entry(parent)
        self.e2.insert(0, 4.2)
        self.e3 = tk.Entry(parent)
        self.e3.insert(0, 'Default text')
        self.e1.grid(row=0, column=1)
        self.e2.grid(row=1, column=1)
        self.e3.grid(row=1, column=3)
        self.cb = tk.Checkbutton(parent, text="Hardcopy")
        self.cb.grid(row=3, columnspan=2, sticky=tk.W)

    def apply(self):
        try:
            first = int(self.e1.get())
            second = float(self.e2.get())
            third = self.e3.get()
            self.result = first, second, third
        except ValueError:
            tk.tkMessageBox.showwarning("Bad input", "Illegal values, please try again")


class StatusBar(tk.Frame):
    def __init__(self, root, master, *args, **kwargs):
        tk.Frame.__init__(self, master, *args, **kwargs)
        self.label = tk.Label(self)
        self.label.grid(row=0, sticky=tk.N + tk.E + tk.S + tk.W)

    def set(self, format, *args):
        self.label.config(text=format % args)
        self.label.update_idletasks()

    def clear(self):
        self.label.config(text="")
        self.label.update_idletasks()


class LeftFrame:
    """
    This class creates and controls the widgets and figures in the left frame which
    are used to display the activation functions.
    Kush Raina 2018_06_03
    """

    def generate_Random_Weights(self,file_name):
            self.weight_Matrix=np.random.uniform(-0.001,0.001,7840)
            
            self.weight_Matrix=np.reshape(self.weight_Matrix,(10,784))
             # reshape to column vector and return it 

       
    def read_one_image_and_convert_to_vector(self,file_name):
        img = scipy.misc.imread(file_name).astype(np.float32) # read image and convert to float
        #print(img.reshape(-1,1))
        return img.reshape(-1,1) # reshape to column vector and return it

    def convert_images_toVectors(self,file_name):
        img = scipy.misc.imread(file_name).astype(np.float32) # read image and convert to float
        #print(img.reshape(-1,1))
        return img.reshape(-1,1) # reshape to column vector and return it
    def read_csv_as_matrix(self,file_name):
        data = np.loadtxt(file_name, skiprows=1, delimiter=',', dtype=np.float32)
        return data  
    
    def normalize(self,img):
        img=np.true_divide(img, 127.5) # read image and convert to float
        img=np.subtract(img,1)
        #print(img)
        return img # reshape to column vector and return it   
    def onhotEncoding(self,train_onehot,train_labels):
         for i in train_labels:
                    if i==0:
                        temp=np.array([1,-1,-1,-1,-1,-1,-1,-1,-1,-1]).reshape(10,1)
                    elif i==1:
                        temp=np.array([-1,1,-1,-1,-1,-1,-1,-1,-1,-1]).reshape(10,1)
                    elif i==2:
                        temp=np.array([-1,-1,1,-1,-1,-1,-1,-1,-1,-1]).reshape(10,1)
                    elif i==3:
                        temp=np.array([-1,-1,-1,1,-1,-1,-1,-1,-1,-1]).reshape(10,1)
                    elif i==4:
                        temp=np.array([-1,-1,-1,-1,1,-1,-1,-1,-1,-1]).reshape(10,1)
                    elif i==5:
                        temp=np.array([-1,-1,-1,-1,-1,1,-1,-1,-1,-1]).reshape(10,1)
                    elif i==6:
                        temp=np.array([-1,-1,-1,-1,-1,-1,1,-1,-1,-1]).reshape(10,1)
                    elif i==7:
                        temp=np.array([-1,-1,-1,-1,-1,-1,-1,1,-1,-1]).reshape(10,1)
                    elif i==8:
                        temp=np.array([-1,-1,-1,-1,-1,-1,-1,-1,1,-1]).reshape(10,1)
                    elif i==9:
                        temp=np.array([-1,-1,-1,-1,-1,-1,-1,-1,-1,1]).reshape(10,1)
                
                    train_onehot=np.column_stack((train_onehot,temp))
        #print(img)
         return train_onehot # reshape to column vector and return it   
     
    def generate_data(self,dataset_name, n_samples, n_classes):
        n_samples=int(n_samples)
        n_classes=int(n_classes)
        if dataset_name == 'swiss_roll':
            data = sklearn.datasets.make_swiss_roll(n_samples, noise=1.5, random_state=99)[0]
            data = data[:, [0, 2]]
        if dataset_name == 'moons':
            data = sklearn.datasets.make_moons(n_samples=n_samples, noise=0.15)[0]
        if dataset_name == 'blobs':
            data = sklearn.datasets.make_blobs(n_samples=n_samples, centers=n_classes*2, n_features=2, cluster_std=0.85*np.sqrt(n_classes), random_state=100)
            return data[0]/10., [i % n_classes for i in data[1]]
        if dataset_name == 's_curve':
            data = sklearn.datasets.make_s_curve(n_samples=n_samples, noise=0.15, random_state=100)[0]
            data = data[:, [0,2]]/3.0
    
        ward = AgglomerativeClustering(n_clusters=n_classes*2, linkage='ward').fit(data)
        return data[:]+np.random.randn(*data.shape)*0.03, [i % n_classes for i in ward.labels_]

    def __init__(self, root, master, debug_print_flag=False):

                self.master = master
                self.root = root
                self.flagvar=False
                #########################################################################
                #  Set up the constants and default values
                #########################################################################
                self.xmin = 0
                self.xmax = 100
                self.ymin = 0
                self.ymax = 100
                self.epochno=0
                
                self.learning_rate = 0.1
                self.lambda_element_value = .01
                self.no_of_nodes_hidden = 100
                self.training_sample=200
                self.classes=4
                
                self.bias = 0.0
                self.activation_type = "Relu"
                self.learning_type="Delta Rule"
                self.x1_samples_new=0
                self.x2_samples_new=0
                self.errorStack=0
                self.errorFullStack=0
                self.epochFullStack=0
                self.counter=0
                self.typeofdata_type="s_curve"
                self.weightHiddenNodes=0;
                self.weightOutputNodes=0;
              

                
                #########################################################################
                #  Set up the plotting frame and controls frame
                #########################################################################
                
                master.rowconfigure(0, weight=1)
                master.columnconfigure(0, weight=1)
                self.plot_frame = tk.Frame(self.master, borderwidth=10, relief=tk.SUNKEN)
                self.plot_frame.grid(row=0, column=0, columnspan=1, sticky=tk.N + tk.E + tk.S + tk.W)
                
                #self.plot_frame.grid(row=0, column=1, columnspan=1, sticky=tk.N + tk.E + tk.S + tk.W)
                self.figure = plt.figure(figsize=(13,8))
                #self.axes = self.figure.add_axes([0.9, 0.9, 0.6, 0.8])
                self.axes = self.figure.add_axes()
                self.axes = self.figure.gca()
                self.axes.set_xlabel('Input')
                self.axes.set_ylabel('Output')
                # self.axes.margins(0.5)
                self.axes.set_title("565151")
                plt.xlim(self.xmin, self.xmax)
                plt.ylim(self.ymin, self.ymax)
                self.canvas = FigureCanvasTkAgg(self.figure, master=self.plot_frame)
                self.plot_widget = self.canvas.get_tk_widget()
                self.plot_widget.grid(row=0, column=1, sticky=tk.N + tk.E + tk.S + tk.W)
                # Create a frame to contain all the controls such as sliders, buttons, ...
                self.controls_frame = tk.Frame(self.master)
                self.controls_frame.grid(row=1, column=0, sticky=tk.N + tk.E + tk.S + tk.W)
                #########################################################################
                #  Set up the control widgets such as sliders and selection boxes
                #########################################################################
                #Number of delayed Elements 
                
                self.lambda_element_slider = tk.Scale(self.controls_frame, variable=tk.DoubleVar(), orient=tk.HORIZONTAL,
                                                    from_=0, to_=1, resolution=.01, bg="#DDDDDD",
                                                    activebackground="#FF0000", highlightcolor="#00FFFF", label="Regularization",
                                                    command=lambda event: self.lambda_element_slider_callback())
                self.lambda_element_slider.set(self.lambda_element_value)
                self.lambda_element_slider.bind("<ButtonRelease-1>", lambda event: self.lambda_element_slider_callback())
                
                self.lambda_element_slider.grid(row=0, column=0, sticky=tk.N + tk.E + tk.S + tk.W)
                #Creating 1st weight slider 
                self.learning_rate_slider = tk.Scale(self.controls_frame, variable=tk.DoubleVar(), orient=tk.HORIZONTAL,
                                                    from_=0.001, to_=1, resolution=0.001, bg="#DDDDDD",
                                                    activebackground="#FF0000", highlightcolor="#00FFFF", label="Learnings Rate",
                                                    command=lambda event: self.learning_rate_slider_callback())
                self.learning_rate_slider.set(self.learning_rate)
                
                
                self.learning_rate_slider.bind("<ButtonRelease-1>", lambda event: self.learning_rate_slider_callback())
                
                self.learning_rate_slider.grid(row=0, column=1, sticky=tk.N + tk.E + tk.S + tk.W)               
 
                self.training_sample_slider = tk.Scale(self.controls_frame, variable=tk.DoubleVar(), orient=tk.HORIZONTAL,
                                                    from_=4, to_=1000, resolution=1, bg="#DDDDDD",
                                                    activebackground="#FF0000", highlightcolor="#00FFFF", label="NumberOfSamples",
                                                    command=lambda event: self.training_sample_slider_callback())
                #Creating 1st weight slider 
                self.training_sample_slider.set(self.training_sample)
                self.training_sample_slider.bind("<ButtonRelease-1>", lambda event: self.training_sample_slider_callback())
                
                self.training_sample_slider.grid(row=0, column=2, sticky=tk.N + tk.E + tk.S + tk.W)  
                
                self.classes_slider = tk.Scale(self.controls_frame, variable=tk.DoubleVar(), orient=tk.HORIZONTAL,
                                                    from_=2, to_=10, resolution=1, bg="#DDDDDD",
                                                    activebackground="#FF0000", highlightcolor="#00FFFF", label="Classes",
                                                    command=lambda event: self.classes_slider_callback())
                #Creating 1st weight slider 
                self.classes_slider.set(self.classes)
                self.classes_slider.bind("<ButtonRelease-1>", lambda event: self.classes_slider_callback())
                
                self.classes_slider.grid(row=0, column=3, sticky=tk.N + tk.E + tk.S + tk.W)                 
 
    
    
    

                                   
            
                
                
                # Randomize weight button
                
                
                
                self.adjust_lms = tk.Button(self.controls_frame, text="Adjust Weights Train", fg="red", width=16,
                                       command=self.train)
                self.adjust_lms.grid(row=0, column=6)
                
                
                
                self.label_for_hiddenLayer_activation_function = tk.Label(self.controls_frame, text="Hidden Layer Transfer Function",
                                                              justify="center")
                self.label_for_hiddenLayer_activation_function.grid(row=0, column=7, sticky=tk.N + tk.E + tk.S + tk.W)
                self.activation_function_variable = tk.StringVar()
                self.activation_function_dropdown = tk.OptionMenu(self.controls_frame, self.activation_function_variable,
                                                                   "Relu","Sigmoid", command=lambda
                        event: self.activation_function_dropdown_callback())
                
                self.activation_function_variable.set(self.activation_type)
                
                self.activation_function_dropdown.grid(row=0, column=7, sticky=tk.N + tk.E + tk.S + tk.W)
                
       
        
        
        
###################################################
                self.label_for_type_of_data_function = tk.Label(self.controls_frame, text="Type of data ",
                                                              justify="center")
                self.label_for_type_of_data_function.grid(row=0, column=8, sticky=tk.N + tk.E + tk.S + tk.W)
                self.typeofdata_function_variable = tk.StringVar()
                self.type_of_data_function_dropdown = tk.OptionMenu(self.controls_frame, self.typeofdata_function_variable,
                                                                   "s_curve","blobs", "swiss_roll","moons",command=lambda
                        event: self.typeofdata_function_dropdown_callback())
                
                self.typeofdata_function_variable.set(self.typeofdata_type)
                
                self.type_of_data_function_dropdown.grid(row=0, column=8, sticky=tk.N + tk.E + tk.S + tk.W)
                
                self.X,self.y=self.generate_data(self.typeofdata_type,self.training_sample,self.classes)
                self.display_Graph()
                self.set_weight = tk.Button(self.controls_frame, text="Reset Weights", fg="red", width=20,
                                       command=self.set_weight_function)
                self.set_weight.grid(row=0, column=5)
                self.set_weight_function()
                self.no_of_nodes_hidden_slider = tk.Scale(self.controls_frame, variable=tk.DoubleVar(), orient=tk.HORIZONTAL,
                                                    from_=1, to_=500, resolution=1, bg="#DDDDDD",
                                                    activebackground="#FF0000", highlightcolor="#00FFFF", label="Number of Hidden Layers",
                                                    command=lambda event: self.no_of_nodes_hidden_slider_callback())
                #Creating 1st weight slider 
                self.no_of_nodes_hidden_slider.set(self.no_of_nodes_hidden)
                self.no_of_nodes_hidden_slider.bind("<ButtonRelease-1>", lambda event: self.no_of_nodes_hidden_slider_callback())
                
                self.no_of_nodes_hidden_slider.grid(row=0, column=4, sticky=tk.N + tk.E + tk.S + tk.W) 

         
                #########################################################################
                #  Set up the frame for drop down selection
                #########################################################################
#                self.label_for_activation_function = tk.Label(self.controls_frame, text="Activation Function Type:",
#                                                              justify="center")
#                self.label_for_activation_function.grid(row=0, column=4, sticky=tk.N + tk.E + tk.S + tk.W)
#                self.activation_function_variable = tk.StringVar()
#                self.activation_function_dropdown = tk.OptionMenu(self.controls_frame, self.activation_function_variable,
#                                                                   "Linear","Hyperbolic Tangent", "Symmetric Hard Limit", command=lambda
#                        event: self.activation_function_dropdown_callback())
#                
#                self.activation_function_variable.set("Symmetric Hard Limit")
#                
#                self.activation_function_dropdown.grid(row=0, column=4, sticky=tk.N + tk.E + tk.S + tk.W)
#                
#                
#                self.label_for_learning_method = tk.Label(self.controls_frame, text="Learning Method Type:",
#                                                              justify="center")
#                self.label_for_learning_method.grid(row=0, column=5, sticky=tk.N + tk.E + tk.S + tk.W)
#                self.learning_method_variable = tk.StringVar()
#                self.learning_method_dropdown = tk.OptionMenu(self.controls_frame, self.learning_method_variable,
#                                                                   "Filtered Learning (Smoothing)","Delta Rule", "Unsupervised Hebb", command=lambda
#                        event: self.learning_method_dropdown_callback())
#                
#                self.learning_method_variable.set("Delta Rule")
#                
#                self.learning_method_dropdown.grid(row=0, column=5, sticky=tk.N + tk.E + tk.S + tk.W)
                
                self.canvas.get_tk_widget().bind("<ButtonPress-1>", self.left_mouse_click_callback)
                self.canvas.get_tk_widget().bind("<ButtonPress-1>", self.left_mouse_click_callback)
                self.canvas.get_tk_widget().bind("<ButtonRelease-1>", self.left_mouse_release_callback)
                self.canvas.get_tk_widget().bind("<B1-Motion>", self.left_mouse_down_motion_callback)
                self.canvas.get_tk_widget().bind("<ButtonPress-3>", self.right_mouse_click_callback)
                self.canvas.get_tk_widget().bind("<ButtonRelease-3>", self.right_mouse_release_callback)
                self.canvas.get_tk_widget().bind("<B3-Motion>", self.right_mouse_down_motion_callback)
                self.canvas.get_tk_widget().bind("<Key>", self.key_pressed_callback)
                self.canvas.get_tk_widget().bind("<Up>", self.up_arrow_pressed_callback)
                self.canvas.get_tk_widget().bind("<Down>", self.down_arrow_pressed_callback)
                self.canvas.get_tk_widget().bind("<Right>", self.right_arrow_pressed_callback)
                self.canvas.get_tk_widget().bind("<Left>", self.left_arrow_pressed_callback)
                self.canvas.get_tk_widget().bind("<Shift-Up>", self.shift_up_arrow_pressed_callback)
                self.canvas.get_tk_widget().bind("<Shift-Down>", self.shift_down_arrow_pressed_callback)
                self.canvas.get_tk_widget().bind("<Shift-Right>", self.shift_right_arrow_pressed_callback)
                self.canvas.get_tk_widget().bind("<Shift-Left>", self.shift_left_arrow_pressed_callback)
                self.canvas.get_tk_widget().bind("f", self.f_key_pressed_callback)
                self.canvas.get_tk_widget().bind("b", self.b_key_pressed_callback)
                

    def key_pressed_callback(self, event):
        self.root.status_bar.set('%s', 'Key pressed')
    def g1(self):
        print('kush')
        
        
    def activation_function_dropdown_callback(self):
        self.activation_type = self.activation_function_variable.get()
        #print('activation type',self.activation_type)
        
        
    def typeofdata_function_dropdown_callback(self):
        self.typeofdata_type = self.typeofdata_function_variable.get()
        self.X,self.y=self.generate_data(self.typeofdata_type,self.training_sample,self.classes)
        self.display_Graph()
        

        #print(cm)
        

    	
                    
               
        

            

        
        

           
    def plotPoints(self):
            self.axes.cla()
            self.axes.set_xlabel('Epoch')
            self.axes.set_ylabel('Error')
            
            self.errorStackPlot=self.errorFullStack[:,1:]
            self.epochnoplot=np.transpose(self.epochno)
            
            self.errorStackPlot=np.transpose(self.errorStackPlot)
            self.axes.plot(self.epochnoplot,self.errorStackPlot)
            #self.axes.plot([2,3,4],[4,5,6])
            self.axes.xaxis.set_visible(True)
            self.axes.set_xlim(self.xmin, self.xmax)
            self.axes.set_ylim(self.ymin, self.ymax)
            
#            plt.xlim(self.xmin, self.xmax)
#            plt.ylim(self.ymin, self.ymax)
#            plt.title(self.activation_type)
            self.canvas.draw()
            

#            plt.plot(self.epochnoplot, self.errorStackPlot, 'ro')
#            plt.axis([0, 1000, 0, 100])
#            plt.show()           
    def predictOutputSmoothing(self):
            #print(self.test.shape)
            testtopredict=np.transpose(self.test)
            #print(testtopredict.shape)
            actualTargets=self.test_onehot
            #print(self.weight_Matrix.shape)
            predictedValues=np.dot(self.weight_Matrix,testtopredict)
            predictedValues=self.calculate_activation_function(predictedValues,self.activation_type)
            self.predectedclassid=np.argmax(predictedValues,axis=0)
            self.actualClassid=np.argmax(actualTargets,axis=0)
        
            
    def predictOutputDelta(self):
            #print(self.test.shape)
            testtopredict=np.transpose(self.test)
            actualTargets=self.test_onehot
            #print(actualTargets)
            #print(testtopredict.shape)
            #print(self.weight_Matrix.shape)
            newWeigh=self.weight_Matrix
            #print(newWeigh)
            predictedValues=np.dot(self.weight_Matrix,testtopredict)
            predictedValues=self.calculate_activation_function(predictedValues,self.activation_type)
            #print(predictedValues)
            self.predectedclassid=np.argmax(predictedValues,axis=0)
            self.actualClassid=np.argmax(actualTargets,axis=0)

           
            #print('kush')            

    def calculate_output_Matrix(self,test1):
            for x in range(0,(np.size(test1,1))):
                    output=np.dot(self.weight_Matrix,test1[:,x])
                    output=self.calculate_activation_function(output,self.activation_type)
                    
                    
    def calculate_error(self):
            correct=np.sum(self.predectedclassid == self.actualClassid)
            error=np.size(self.actualClassid,0)-correct
            errorPercent=((error)/np.size(self.actualClassid,0)*100)
            self.errorStack=np.column_stack((self.errorStack,errorPercent))    
            
    def next_batch(self,num, data, labels):
        '''
        Return a total of `num` random samples and labels. 
        '''
        idx = np.arange(0 , len(data))
        np.random.shuffle(idx)
        idx = idx[:num]
        data_shuffle = [data[ i] for i in idx]
        labels_shuffle = [labels[ i] for i in idx]
    
        return np.asarray(data_shuffle), np.asarray(labels_shuffle)


    def onehotEncoding(self,train_onehot,train_labels):
        count = 0
        for i in train_labels:
            train_onehot[count,i]=1
            count += 1
        return train_onehot            
            
    def set_weight_function(self):
        
            
            self.weightH1 = np.random.uniform(-0.001,0.001, 2*self.no_of_nodes_hidden)
            self.weightH1 = self.weightH1.reshape(2, self.no_of_nodes_hidden)
            self.weightH2 = np.random.uniform(-0.001,0.001, self.no_of_nodes_hidden*self.classes)
            self.weightH2 = self.weightH2.reshape(self.no_of_nodes_hidden, self.classes)
            self.bias1=np.zeros(self.no_of_nodes_hidden)
            self.bias_output=np.zeros(self.classes)
            self.display_Graph()
            
            
    def set_weight_function1(self):
        
            
            self.weightH1 = np.random.uniform(-0.001,0.001, 2*self.no_of_nodes_hidden)
            self.weightH1 = self.weightH1.reshape(2, self.no_of_nodes_hidden)
            self.weightH2 = np.random.uniform(-0.001,0.001, self.no_of_nodes_hidden*self.classes)
            self.weightH2 = self.weightH2.reshape(self.no_of_nodes_hidden, self.classes)
            self.bias1=np.zeros(self.no_of_nodes_hidden)
            self.bias_output=np.zeros(self.classes)
            #self.display_Graph()           
    
    
    def train(self):
            #print('size 1',np.size(self.priceaftermean,1))
            #print('size 0',np.size(self.priceaftermean,0))
            self.count=0
            input_data=self.X
            input_labels=self.y
            n_samples=self.training_sample
            # set parameters
            learning_rate = self.learning_rate
            training_epochs = 10
            batch_size = 10 # number of images to classify
            display_step = 1
            
            # set network parameters
            nodes_layer_1 = self.no_of_nodes_hidden # number of nodes in the first layer
            
            nodes_input = 2 # number of input nodes: MNIST image input is 784 pixels (image size is 28 x 28 pixels)
            nodes_classes = self.classes # number of output nodes: network output is digits 0-9
            
            
            
            train_onehot = np.zeros((input_data.shape[0], nodes_classes))
            input_one_hot = self.onehotEncoding(train_onehot, input_labels)
            
            
            
            beta = tf.constant(self.lambda_element_value,dtype=tf.float32)
            x = tf.placeholder('float', [None, nodes_input]) # input tensor x is [1 x 784] representing the image to classify
            y = tf.placeholder('float', [None, nodes_classes]) # output tensor y equals the number of classes (i.e., digits 0 to 9)
            
            
            
#            weightH1 = np.random.uniform(-0.001,0.001, nodes_input*nodes_layer_1)
#            weightH1 = weightH1.reshape(nodes_input, nodes_layer_1)
#            weightH2 = np.random.uniform(-0.001,0.001, nodes_layer_1*nodes_classes)
#            weightH2 = weightH2.reshape(nodes_layer_1, nodes_classes)
            
            weights_layer_1 = tf.Variable(self.weightH1,dtype=tf.float32) # weights tensor for layer 1 is size [784 x 256]
            bias_layer_1 = tf.Variable(self.bias1,dtype=tf.float32) # bias layer 1 tensor
            # each layer 1 neuron receives input image pixels multiplied by layer 1 weights and added to values of the layer 1 biases tensor
            # each layer 1 neuron sends its output to the neurons of layer 2 via the activation function, in this case a sigmoid function
            if self.activation_type == "Relu":
            
                layer_1 = tf.nn.relu(tf.add(tf.matmul(x, weights_layer_1), bias_layer_1))
            else:
                layer_1 = tf.nn.sigmoid(tf.add(tf.matmul(x, weights_layer_1), bias_layer_1))
            
            output = tf.Variable(self.weightH2,dtype=tf.float32) # weights tensor for output layer is size [256 x 10]
            bias_output = tf.Variable(self.bias_output,dtype=tf.float32) # bias output tensor
            # the output layer receives inputs from the neurons of layer 2 multiplied by output weights and added to values of the output biases tensor
            # output layer will be converted to a probability for each digit 0 to 9 (i.e., highest probability is the best guess for what the digit is)
            output_layer = tf.matmul(layer_1, output) + bias_output
            #print(output_layer)
            # cost function
            cost = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits=output_layer, labels=y))
            regularizers = tf.nn.l2_loss(weights_layer_1) + tf.nn.l2_loss(output)
            
            cost = tf.reduce_mean(cost +   tf.cast(regularizers, tf.float32)*beta)
            
            # optimizer (adam optimizer controls the learning rate, could also use gradient descent optimizer)
            optimizer = tf.train.AdamOptimizer(learning_rate=learning_rate).minimize(cost)
            
            predection=tf.argmax(output_layer, 1)
            actual=tf.argmax(y, 1)
            correct_prediction = tf.equal(tf.argmax(output_layer, 1), tf.argmax(y, 1))
            
                # calculate accuracy
            accuracy = tf.reduce_mean(tf.cast(correct_prediction, 'float'))
            # plot settings
            avg_set = []
            epoch_set = []
            
            # initialize variables
            init = tf.initialize_all_variables()
            
            
            with tf.Session() as sess:
                sess.run(init)

    # define training cycle
                for epoch in range(10):
                    
                    # loop over all 100 batches
                    
                        # fit training using batch data
                        
#                        n_batches = int(n_samples/ batch_size)
#                        
#                        for i in range(n_batches):
#                            batch_x, batch_y = self.next_batch(batch_size,input_data,input_one_hot)
#                        
#                            logits_a, acc,accuracte=sess.run([optimizer,cost,accuracy], feed_dict = {x: batch_x, y: batch_y})
#                            print("Optimization Finished!", " ", logits_a, "\n ", acc)
                        
                        
                        # calculate average loss
                        #avg_cost += sess.run(cost, feed_dict = {x: batch_xs, y: batch_ys}) / total_batch
                    # display logs per epoch step
                        _accuracy,_actual,_cost,_optimizer,self.weightH1,self.bias1,self.weightH2,self.bias_output,acc=sess.run([predection,actual,cost,optimizer,weights_layer_1,bias_layer_1,output,bias_output,accuracy], feed_dict = {x: input_data, y: input_one_hot})
                        
                        #_accuracy,_actual,_cost,_optimizer=sess.run([predection,actual,cost,optimizer], feed_dict = {x: input_data, y: input_one_hot})
                        
                        
                        print("predected  --- actual --- cost--  acc ",_accuracy,_actual,_cost,acc)
                        self.axes.cla()
                        ax=plt.scatter(input_data[:, 0], input_data[:, 1], c=_accuracy, cmap=plt.cm.Accent)
#                        plt.suptitle(self.typeofdata_type,fontsize=20)
                        self.axes.scatter(input_data[:, 0], input_data[:, 1], c=_accuracy, cmap=plt.cm.Accent)
                        self.axes.set_title(self.typeofdata_type)
            	# ax.set_title("S Curve")
                        plt.show()
            #self.axes.scatter(self.x2_samples_old[:,0],self.x2_samples_old[:,1], c= 'black', marker='o')
            
            
                        self.axes.xaxis.set_visible(True)
                        self.canvas.draw()

                print('Training phase finished')
    # plot training phase
   

    # test model

                #print("accurcy ",accuracte)

            
            
            
            
            

            

                

    def calculate_errors_test(self):
            self.errorTest=0
            self.countTest=0
            self.maearray=0
            self.errorMAEFinal=0
            self.initial=0
            self.left=np.size(self.X_test,0)
            while(self.left>self.stride):
                
                a=self.X_test
                end=(self.delayed_element_value+1)+(self.initial*self.stride)
                start=end-(self.delayed_element_value+1)
                
                self.testVal=np.row_stack(((self.X_test[start:end,:]),(self.y_test[start:end,:])))
                self.targetTest=self.X_test[end,:]
                self.actual=np.dot(self.weights,self.testVal)+self.bias
                self.errorTest=self.errorTest+(self.targetTest-self.actual)**2
                self.errorMAE=self.targetTest-self.actual
                self.errorMAE=np.absolute(self.errorMAE)
                self.errorMAEFinal=self.errorMAEFinal+self.errorMAE
                self.countTest=self.countTest+1
                
                self.left=np.size(self.X_test,0)-((self.delayed_element_value+1)+(self.initial*self.stride))
                self.initial=self.initial+1
            
            self.errorTest=self.errorTest/self.countTest
            self.errorMAEFinal=self.errorMAEFinal/self.countTest
            
    def calculate_errors_Direct_test(self):
            self.errorTest=0
            self.countTest=0
            self.maearray=0
            self.errorMAEFinal=0
            self.initial=0
            self.left=np.size(self.X_test,0)
            while(self.left>self.stride):
                
                a=self.X_test
                end=(self.delayed_element_value+1)+(self.initial*self.stride)
                start=end-(self.delayed_element_value+1)
                
                self.testVal=np.row_stack(((self.X_test[start:end,:]),(self.y_test[start:end,:])))
                self.testVal=np.row_stack(((self.testVal),1))
                self.targetTest=self.X_test[end,:]
                self.actual=np.dot(np.transpose(self.weights1),self.testVal)
                self.errorTest=self.errorTest+(self.targetTest-self.actual)**2
                self.errorMAE=self.targetTest-self.actual
                self.errorMAE=np.absolute(self.errorMAE)
                self.errorMAEFinal=self.errorMAEFinal+self.errorMAE
                self.countTest=self.countTest+1
                
                self.left=np.size(self.X_test,0)-((self.delayed_element_value+1)+(self.initial*self.stride))
                self.initial=self.initial+1
            
            self.errorTest=self.errorTest/self.countTest
            print('MSE',self.errorTest )
            self.errorMAEFinal=self.errorMAEFinal/self.countTest       
            print('MAE',self.errorMAEFinal )
            self.axes.cla()
            self.axes.set_title("Direct Learning + for MSE 0 for MAE")
            self.axes.set_xlabel('Epoch')
            self.axes.set_ylabel('Error')
            
            self.axes.scatter(0.5,self.errorTest, marker='+')
            self.axes.scatter(0.5,self.errorMAEFinal, c= 'black', marker='o')
            max1=np.max(self.errorTest)
            max2=np.max(self.errorMAEFinal)
            #plt.plot(self.itr_array, self.error_array,'ro')
            #plt.axis([0, 10, 0, 0.1])
            #self.axes.set_xlim(0, 1)
           # self.axes.set_ylim(self.ymin, max1+max2)
            self.axes.xaxis.set_visible(True)
            self.canvas.draw()
            
            

           

    def adjust_weights_direct_func(self):
            
#                initial=0
#                self.dataVal=np.row_stack((self.X_train,self.y_train))
#                self.R=np.dot(self.dataVal,np.transpose(self.dataVal))
#                print(np.size(self.dataVal,0))
#                a=self.dataVal
#                self.dataVal=self.dataVal/((np.size(self.dataVal,0))/2)
#                b=self.dataVal
#                self.h=np.dot(self.X_train,self.dataVal)
                self.countTest=0
                self.initial=0
                self.targetFinal=0
                self.testValFinal=0
                self.left=np.size(self.X_train,0)
                self.targetFinal=0
                self.testValFinal=0
                while(self.left>self.stride):
                
                    a=self.X_test
                    end=(self.delayed_element_value+1)+(self.initial*self.stride)
                    start=end-(self.delayed_element_value+1)
                
                    self.testVal=np.row_stack(((self.X_train[start:end,:]),(self.y_train[start:end,:])))
                    self.testVal=np.row_stack(((self.testVal),1))
                    self.targetTest=self.X_train[end,:]
                    
                    self.targetFinal=self.targetFinal+self.targetTest*self.testVal
                    a=self.targetFinal
                    
                    self.testValFinal=self.testValFinal+np.dot(self.testVal,np.transpose(self.testVal))
                    
                    
                    b=self.testValFinal
                    #self.testValFinal=np.row_stack(((self.testValFinal),(self.testVal)))
                    
                    #self.targetFinal=np.column_stack(((self.targetFinal),(self.targetTest)))
                    self.countTest=self.countTest+1
                    
                
                    self.left=np.size(self.X_train,0)-((self.delayed_element_value+1)+(self.initial*self.stride))
                    self.initial=self.initial+1
                self.targetFinal=self.targetFinal/self.countTest
                h=self.targetFinal
                self.testValFinal=self.testValFinal/self.countTest
                r=self.testValFinal
                local=inv(r)
                
                #self.weights1=np.dot(local,self.targetFinal)
                self.weights1=local.dot(self.targetFinal)
                test1=self.weights1
                test=np.transpose(self.weights1)
                self.weights=test[:,:-1]
                self.calculate_errors_Direct_test()
                #print(testValFinal)
                
            
                
            
  
    def calculate_activation_function(self,net_value,type='Symmetric Hard Limit'):
        
        
        
            if type == "Linear":
                activation = net_value
            elif type == "Hyperbolic Tangent":
                activation = np.tanh(net_value) 
            elif type == "Symmetric Hard Limit":
                #print(net_value)
                temp1 = np.ma.masked_where(net_value < 0, net_value, copy=False)
                #print(temp1)
                temp2 = temp1.filled(-1)
                #print(temp2)
                temp3 = np.ma.masked_where(temp2 > 0, temp2, copy=False)
                activation = temp3.filled(1)
                #print(activation)
            #print(activation)
           
            
            
            
            return activation        
            
        
    def Random_Gen(self):
        self.weight_Matrix=np.random.uniform(-0.001,0.001,7850)
        self.weight_Matrix=np.reshape(self.weight_Matrix,(10,785))

        
   
            
        
        
        
    def display_Graph(self):
        
            self.axes.cla()
            #self.axes.set_xlim(0, 100)
            #self.axes.set_ylim(0, 100)
#            self.axes.set_xlabel('Input')
#            self.axes.set_ylabel('Output')
#            fig = plt.figure()
#            ax = fig.gca()
#            resolution=100
#            xs = np.linspace(-10., 10., resolution)
#            ys = np.linspace(-10., 10., resolution)
#            xx, yy = np.meshgrid(xs, ys)
#            zz=self.input_weight*xx+self.input_weight1*yy+self.bias
#            zz[zz<0]=-1
#            zz[zz>0]=+1
#            quad = ax.pcolormesh(xs, ys, zz)
#            color=ListedColormap(['r','g'])
#            plt.scatter(self.x1_samples_old[:,0],self.x1_samples_old[:,1], marker='+')
#            plt.scatter(self.x2_samples_old[:,0],self.x2_samples_old[:,1], c= 'black', marker='o')
#       
#            self.axes.plot(quad)
#            self.axes.pcolormesh(xs, ys, zz,cmap=color)
            #self.axes.pcolormesh()
            ax=plt.scatter(self.X[:, 0], self.X[:, 1], c=self.y, cmap=plt.cm.Accent)
#            plt.suptitle(self.typeofdata_type,fontsize=20)
            plt.show()
            self.axes.scatter(self.X[:, 0], self.X[:, 1], c=self.y, cmap=plt.cm.Accent)
            #self.axes.scatter(self.x2_samples_old[:,0],self.x2_samples_old[:,1], c= 'black', marker='o')
            self.axes.set_title(self.typeofdata_type)
            self.axes.xaxis.set_visible(True)
            
            
            	# ax.set_title("S Curve")
           
#            plt.xlim(self.xmin, self.xmax)
#            plt.ylim(self.ymin, self.ymax)
#            plt.title(self.activation_type)
            self.canvas.draw()
#            print('activation type',self.activation_type)
#            if self.activation_type=='Linear':
#            self.input_weight_slider_callback()
#            self.input_weight_slider_callback1()
#            self.bias_slider_callback()
#            
#            self.flagvar = True
        
        
    def redrawline(self):
            self.axes.cla()
            self.axes.set_xlabel('Input')
            self.axes.set_ylabel('Output')
            fig = plt.figure()
            ax = fig.gca()
            resolution=500
            xs = np.linspace(-10., 10., resolution)
            ys = np.linspace(-10., 10., resolution)
            xx, yy = np.meshgrid(xs, ys)
            zz=self.input_weight*xx+self.input_weight1*yy+self.bias
            zz[zz<0]=-1
            zz[zz>0]=+1
            quad = ax.pcolormesh(xs, ys, zz)
            c=ListedColormap(['r','g'])
            plt.scatter(self.x1_samples_old[:,0],self.x1_samples_old[:,1], marker='+')
            plt.scatter(self.x2_samples_old[:,0],self.x2_samples_old[:,1], c= 'black', marker='o')
       
        #self.axes.plot(quad)
            self.axes.pcolormesh(xs, ys, zz,cmap=c)
            #self.axes.pcolormesh()
            
            self.axes.scatter(self.x1_samples_old[:,0],self.x1_samples_old[:,1], marker='+')
            self.axes.scatter(self.x2_samples_old[:,0],self.x2_samples_old[:,1], c= 'black', marker='o')
            plt.show()
            self.axes.xaxis.set_visible(True)
            plt.xlim(self.xmin, self.xmax)
            plt.ylim(self.ymin, self.ymax)
            plt.title(self.activation_type)
            self.canvas.draw()
        
    def up_arrow_pressed_callback(self, event):
        self.root.status_bar.set('%s', "Up arrow was pressed")

    def down_arrow_pressed_callback(self, event):
        self.root.status_bar.set('%s', "Down arrow was pressed")

    def right_arrow_pressed_callback(self, event):
        self.root.status_bar.set('%s', "Right arrow was pressed")

    def left_arrow_pressed_callback(self, event):
        self.root.status_bar.set('%s', "Left arrow was pressed")

    def shift_up_arrow_pressed_callback(self, event):
        self.root.status_bar.set('%s', "Shift up arrow was pressed")

    def shift_down_arrow_pressed_callback(self, event):
        self.root.status_bar.set('%s', "Shift down arrow was pressed")

    def shift_right_arrow_pressed_callback(self, event):
        self.root.status_bar.set('%s', "Shift right arrow was pressed")

    def shift_left_arrow_pressed_callback(self, event):
        self.root.status_bar.set('%s', "Shift left arrow was pressed")

    def f_key_pressed_callback(self, event):
        self.root.status_bar.set('%s', "f key was pressed")

    def b_key_pressed_callback(self, event):
        self.root.status_bar.set('%s', "b key was pressed")

    def left_mouse_click_callback(self, event):
        self.root.status_bar.set('%s', 'Left mouse button was clicked. ' + 'x=' + str(event.x) + '   y=' + str(
            event.y))
        self.x = event.x
        self.y = event.y
        self.canvas.focus_set()

    def left_mouse_release_callback(self, event):
        self.root.status_bar.set('%s',
                                 'Left mouse button was released. ' + 'x=' + str(event.x) + '   y=' + str(event.y))
        self.x = None
        self.y = None

    def left_mouse_down_motion_callback(self, event):
        self.root.status_bar.set('%s', 'Left mouse down motion. ' + 'x=' + str(event.x) + '   y=' + str(event.y))
        self.x = event.x
        self.y = event.y

    def right_mouse_click_callback(self, event):
        self.root.status_bar.set('%s', 'Right mouse down motion. ' + 'x=' + str(event.x) + '   y=' + str(event.y))
        self.x = event.x
        self.y = event.y

    def right_mouse_release_callback(self, event):
        self.root.status_bar.set('%s',
                                 'Right mouse button was released. ' + 'x=' + str(event.x) + '   y=' + str(event.y))
        self.x = None
        self.y = None

    def right_mouse_down_motion_callback(self, event):
        self.root.status_bar.set('%s', 'Right mouse down motion. ' + 'x=' + str(event.x) + '   y=' + str(event.y))
        self.x = event.x
        self.y = event.y

    def left_mouse_click_callback(self, event):
        self.root.status_bar.set('%s', 'Left mouse button was clicked. ' + 'x=' + str(event.x) + '   y=' + str(
            event.y))
        self.x = event.x
        self.y = event.y
    
    # self.focus_set()
    def display_activation_function(self):
        input_values = np.linspace(-10, 10, 256, endpoint=True)
        
        print('values ',input_values)
        
        print('weights ',self.input_weight)
        activation = Raina_01_02.calculate_activation_function(self.input_weight,self.input_weight1, self.bias, self.x1_samples_new,self.x2_samples_new,
                                                                  self.activation_type)
        self.axes.cla()
        self.axes.set_xlabel('Input1')
        self.axes.set_ylabel('Output')
        

        self.axes.xaxis.set_visible(True)
        plt.xlim(self.xmin, self.xmax)
        plt.ylim(self.ymin, self.ymax)
        plt.title(self.activation_type)
        self.canvas.draw()

    def input_weight_slider_callback(self):
        self.input_weight = np.float(self.input_weight_slider.get())
        if self.flagvar:
            self.redrawline()
    def delayed_element_slider_callback(self):
        self.delayed_element_value = self.delayed_element_slider.get()
        self.set_weight_zero_function()
        
    def lambda_element_slider_callback(self):
        self.lambda_element_value = self.lambda_element_slider.get()
        #self.set_weight_zero_function()         
    
    
    def classes_slider_callback(self):
        self.classes = self.classes_slider.get()
        self.X,self.y=self.generate_data(self.typeofdata_type,self.training_sample,self.classes)
        self.display_Graph()
        self.set_weight_function()
        
        #self.set_weight_zero_function()  

        
    def no_of_nodes_hidden_slider_callback(self):
        self.no_of_nodes_hidden = self.no_of_nodes_hidden_slider.get()
        self.set_weight_function1()
                
        
        
    def learning_rate_slider_callback(self):
        self.learning_rate = np.float(self.learning_rate_slider.get())
        
            
    def training_sample_slider_callback(self):
        self.training_sample = np.float(self.training_sample_slider.get())
        self.X,self.y=self.generate_data(self.typeofdata_type,self.training_sample,self.classes)
        self.display_Graph()        
        


    def stride_slider_callback(self):
        self.stride = self.stride_slider.get()
        
            
            
    def no_of_iterations_slider_callback(self):
        self.no_of_iterations = self.no_of_iterations_slider.get()
        
        
    def input_weight_slider_callback1(self):
        self.input_weight1 = np.float(self.input_weight_slider1.get())
        if self.flagvar:
            self.redrawline()
        
    def bias_slider_callback(self):
        self.bias = np.float(self.bias_slider.get())
        if self.flagvar:
            self.redrawline()

    def activation_function_dropdown_callback(self):
        self.activation_type = self.activation_function_variable.get()
        print(self.activation_type)
        #self.display_activation_function()
    def learning_method_dropdown_callback(self):
        self.learning_type = self.learning_method_variable.get()
        print(self.activation_type)

class RightFrame:
    """
    This class is for creating right frame widgets which are used to draw graphics
    on canvas as well as embedding matplotlib figures in the tkinter.
    Kush Raina 2018_06_03
    """

    def __init__(self, root, master, debug_print_flag=False):
        self.root = root
        self.master = master
        self.debug_print_flag = debug_print_flag
        width_px = root.winfo_screenwidth()
        height_px = root.winfo_screenheight()
        width_mm = root.winfo_screenmmwidth()
        height_mm = root.winfo_screenmmheight()
        # 2.54 cm = in
        width_in = width_mm / 25.4
        height_in = height_mm / 25.4
        width_dpi = width_px / width_in
        height_dpi = height_px / height_in
        if self.debug_print_flag:
            print('Width: %i px, Height: %i px' % (width_px, height_px))
            print('Width: %i mm, Height: %i mm' % (width_mm, height_mm))
            print('Width: %f in, Height: %f in' % (width_in, height_in))
            print('Width: %f dpi, Height: %f dpi' % (width_dpi, height_dpi))
        # self.canvas = self.master.canvas
        #########################################################################
        #  Set up the plotting frame and controls frame
        #########################################################################
        master.rowconfigure(0, weight=10, minsize=200)
        master.columnconfigure(0, weight=1)
        master.rowconfigure(1, weight=1, minsize=20)
        self.right_frame = tk.Frame(self.master, borderwidth=10, relief='sunken')
        self.right_frame.grid(row=0, column=0, columnspan=1, sticky=tk.N + tk.E + tk.S + tk.W)
        self.matplotlib_width_pixel = self.right_frame.winfo_width()
        self.matplotlib_height_pixel = self.right_frame.winfo_height()
        # set up the frame which contains controls such as sliders and buttons
        self.controls_frame = tk.Frame(self.master)
        self.controls_frame.grid(row=1, column=0, sticky=tk.N + tk.E + tk.S + tk.W)
        self.controls_frame.rowconfigure(1, weight=1, minsize=20)
        self.draw_button = tk.Button(self.controls_frame, text="Draw", fg="red", width=16,
                                     command=self.graphics_draw_callback)
        self.plot_2d_button = tk.Button(self.controls_frame, text="Plot 2D", fg="red", width=16,
                                        command=self.matplotlib_plot_2d_callback)
        self.plot_3d_button = tk.Button(self.controls_frame, text="Plot 3D", fg="red", width=16,
                                        command=self.matplotlib_plot_3d_callback)
        self.draw_button.grid(row=0, column=0)
        self.plot_2d_button.grid(row=0, column=1)
        self.plot_3d_button.grid(row=0, column=2)
        self.right_frame.update()
        self.canvas = tk.Canvas(self.right_frame, relief='ridge', width=self.right_frame.winfo_width() - 110,
                                height=self.right_frame.winfo_height())
        if self.debug_print_flag:
            print("Right frame width, right frame height : ", self.right_frame.winfo_width(),
                  self.right_frame.winfo_height())
        self.canvas.rowconfigure(0, weight=1)
        self.canvas.columnconfigure(0, weight=1)
        self.canvas.grid(row=0, column=0, sticky=tk.N + tk.E + tk.S + tk.W)
        self.canvas.bind("<ButtonPress-1>", self.left_mouse_click_callback)
        self.canvas.bind("<ButtonRelease-1>", self.left_mouse_release_callback)
        self.canvas.bind("<B1-Motion>", self.left_mouse_down_motion_callback)
        self.canvas.bind("<ButtonPress-3>", self.right_mouse_click_callback)
        self.canvas.bind("<ButtonRelease-3>", self.right_mouse_release_callback)
        self.canvas.bind("<B3-Motion>", self.right_mouse_down_motion_callback)
        self.canvas.bind("<Key>", self.key_pressed_callback)
        self.canvas.bind("<Up>", self.up_arrow_pressed_callback)
        self.canvas.bind("<Down>", self.down_arrow_pressed_callback)
        self.canvas.bind("<Right>", self.right_arrow_pressed_callback)
        self.canvas.bind("<Left>", self.left_arrow_pressed_callback)
        self.canvas.bind("<Shift-Up>", self.shift_up_arrow_pressed_callback)
        self.canvas.bind("<Shift-Down>", self.shift_down_arrow_pressed_callback)
        self.canvas.bind("<Shift-Right>", self.shift_right_arrow_pressed_callback)
        self.canvas.bind("<Shift-Left>", self.shift_left_arrow_pressed_callback)
        self.canvas.bind("f", self.f_key_pressed_callback)
        self.canvas.bind("b", self.b_key_pressed_callback)
        # Create a figure for 2d plotting
        self.matplotlib_2d_fig = mpl.figure.Figure()
        # self.matplotlib_2d_fig.set_size_inches(4,2)
        self.matplotlib_2d_fig.set_size_inches((self.right_frame.winfo_width() / width_dpi) - 0.5,
                                               self.right_frame.winfo_height() / height_dpi)
        self.matplotlib_2d_ax = self.matplotlib_2d_fig.add_axes([.1, .1, .7, .7])
        if self.debug_print_flag:
            print("Matplotlib figsize in inches: ", (self.right_frame.winfo_width() / width_dpi) - 0.5,
                  self.right_frame.winfo_height() / height_dpi)
        self.matplotlib_2d_fig_x, self.matplotlib_2d_fig_y = 0, 0
        self.matplotlib_2d_fig_loc = (self.matplotlib_2d_fig_x, self.matplotlib_2d_fig_y)
        # fig = plt.figure()
        # ax = fig.gca(projection='3d')
        # Create a figure for 3d plotting
        self.matplotlib_3d_fig = mpl.figure.Figure()
        self.matplotlib_3d_figure_canvas_agg = FigureCanvasAgg(self.matplotlib_3d_fig)
        # self.matplotlib_2d_fig.set_size_inches(4,2)
        self.matplotlib_3d_fig.set_size_inches((self.right_frame.winfo_width() / width_dpi) - 0.5,
                                               self.right_frame.winfo_height() / height_dpi)
        self.matplotlib_3d_ax = self.matplotlib_3d_fig.add_axes([.1, .1, .6, .6], projection='3d')
        self.matplotlib_3d_fig_x, self.matplotlib_3d_fig_y = 0, 0
        self.matplotlib_3d_fig_loc = (self.matplotlib_3d_fig_x, self.matplotlib_3d_fig_y)

    def display_matplotlib_figure_on_tk_canvas(self):
        # Draw a matplotlib figure in a Tk canvas
        self.matplotlib_2d_ax.clear()
        X = np.linspace(0, 2 * np.pi, 100)
        # Y = np.sin(X)
        Y = np.sin(X * np.int((np.random.rand() + .1) * 10))
        self.matplotlib_2d_ax.plot(X, Y)
        self.matplotlib_2d_ax.set_xlim([0, 2 * np.pi])
        self.matplotlib_2d_ax.set_ylim([-1, 1])
        self.matplotlib_2d_ax.grid(True, which='both')
        self.matplotlib_2d_ax.axhline(y=0, color='k')
        self.matplotlib_2d_ax.axvline(x=0, color='k')
        # plt.subplots_adjust(left=0.0, right=1.0, bottom=0.0, top=1.0)
        # Place the matplotlib figure on canvas and display it
        self.matplotlib_2d_figure_canvas_agg = FigureCanvasAgg(self.matplotlib_2d_fig)
        self.matplotlib_2d_figure_canvas_agg.draw()
        self.matplotlib_2d_figure_x, self.matplotlib_2d_figure_y, self.matplotlib_2d_figure_w, \
        self.matplotlib_2d_figure_h = self.matplotlib_2d_fig.bbox.bounds
        self.matplotlib_2d_figure_w, self.matplotlib_2d_figure_h = int(self.matplotlib_2d_figure_w), int(
            self.matplotlib_2d_figure_h)
        self.photo = tk.PhotoImage(master=self.canvas, width=self.matplotlib_2d_figure_w,
                                   height=self.matplotlib_2d_figure_h)
        # Position: convert from top-left anchor to center anchor
        self.canvas.create_image(self.matplotlib_2d_fig_loc[0] + self.matplotlib_2d_figure_w / 2,
                                 self.matplotlib_2d_fig_loc[1] + self.matplotlib_2d_figure_h / 2, image=self.photo)
        tkagg.blit(self.photo, self.matplotlib_2d_figure_canvas_agg.get_renderer()._renderer, colormode=2)
        self.matplotlib_2d_fig_w, self.matplotlib_2d_fig_h = self.photo.width(), self.photo.height()
        self.canvas.create_text(0, 0, text="Sin Wave", anchor="nw")

    def display_matplotlib_3d_figure_on_tk_canvas(self):
        self.matplotlib_3d_ax.clear()
        r = np.linspace(0, 6, 100)
        temp=np.random.rand()
        theta = np.linspace(-temp * np.pi, temp * np.pi, 40)
        r, theta = np.meshgrid(r, theta)
        X = r * np.sin(theta)
        Y = r * np.cos(theta)
        Z = np.sin(np.sqrt(X ** 2 + Y ** 2))
        surf = self.matplotlib_3d_ax.plot_surface(X, Y, Z, rstride=1, cstride=1, cmap="coolwarm", linewidth=0, antialiased=False);
        # surf = self.matplotlib_3d_ax.plot_surface(X, Y, Z, rcount=1, ccount=1, cmap='bwr', edgecolor='none');
        self.matplotlib_3d_ax.set_xlim(-6, 6)
        self.matplotlib_3d_ax.set_ylim(-6, 6)
        self.matplotlib_3d_ax.set_zlim(-1.01, 1.01)
        self.matplotlib_3d_ax.zaxis.set_major_locator(LinearLocator(10))
        self.matplotlib_3d_ax.zaxis.set_major_formatter(FormatStrFormatter('%.02f'))
        # Place the matplotlib figure on canvas and display it
        self.matplotlib_3d_figure_canvas_agg.draw()
        self.matplotlib_3d_figure_x, self.matplotlib_3d_figure_y, self.matplotlib_3d_figure_w, \
        self.matplotlib_3d_figure_h = self.matplotlib_2d_fig.bbox.bounds
        self.matplotlib_3d_figure_w, self.matplotlib_3d_figure_h = int(self.matplotlib_3d_figure_w), int(
            self.matplotlib_3d_figure_h)
        if self.debug_print_flag:
            print("Matplotlib 3d figure x, y, w, h: ", self.matplotlib_3d_figure_x, self.matplotlib_3d_figure_y,
                  self.matplotlib_3d_figure_w, self.matplotlib_3d_figure_h)
        self.photo = tk.PhotoImage(master=self.canvas, width=self.matplotlib_3d_figure_w,
                                   height=self.matplotlib_3d_figure_h)
        # Position: convert from top-left anchor to center anchor
        self.canvas.create_image(self.matplotlib_3d_fig_loc[0] + self.matplotlib_3d_figure_w / 2,
                                 self.matplotlib_3d_fig_loc[1] + self.matplotlib_3d_figure_h / 2, image=self.photo)
        tkagg.blit(self.photo, self.matplotlib_3d_figure_canvas_agg.get_renderer()._renderer, colormode=2)
        self.matplotlib_3d_fig_w, self.matplotlib_3d_fig_h = self.photo.width(), self.photo.height()

    def key_pressed_callback(self, event):
        self.root.status_bar.set('%s', 'Key pressed')

    def up_arrow_pressed_callback(self, event):
        self.root.status_bar.set('%s', "Up arrow was pressed")

    def down_arrow_pressed_callback(self, event):
        self.root.status_bar.set('%s', "Down arrow was pressed")

    def right_arrow_pressed_callback(self, event):
        self.root.status_bar.set('%s', "Right arrow was pressed")

    def left_arrow_pressed_callback(self, event):
        self.root.status_bar.set('%s', "Left arrow was pressed")

    def shift_up_arrow_pressed_callback(self, event):
        self.root.status_bar.set('%s', "Shift up arrow was pressed")

    def shift_down_arrow_pressed_callback(self, event):
        self.root.status_bar.set('%s', "Shift down arrow was pressed")

    def shift_right_arrow_pressed_callback(self, event):
        self.root.status_bar.set('%s', "Shift right arrow was pressed")

    def shift_left_arrow_pressed_callback(self, event):
        self.root.status_bar.set('%s', "Shift left arrow was pressed")

    def f_key_pressed_callback(self, event):
        self.root.status_bar.set('%s', "f key was pressed")

    def b_key_pressed_callback(self, event):
        self.root.status_bar.set('%s', "b key was pressed")

    def left_mouse_click_callback(self, event):
        self.root.status_bar.set('%s', 'Left mouse button was clicked. ' + 'x=' + str(event.x) + '   y=' + str(
            event.y))
        self.x = event.x
        self.y = event.y
        self.canvas.focus_set()

    def left_mouse_release_callback(self, event):
        self.root.status_bar.set('%s',
                                 'Left mouse button was released. ' + 'x=' + str(event.x) + '   y=' + str(event.y))
        self.x = None
        self.y = None

    def left_mouse_down_motion_callback(self, event):
        self.root.status_bar.set('%s', 'Left mouse down motion. ' + 'x=' + str(event.x) + '   y=' + str(event.y))
        self.x = event.x
        self.y = event.y

    def right_mouse_click_callback(self, event):
        self.root.status_bar.set('%s', 'Right mouse down motion. ' + 'x=' + str(event.x) + '   y=' + str(event.y))
        self.x = event.x
        self.y = event.y

    def right_mouse_release_callback(self, event):
        self.root.status_bar.set('%s',
                                 'Right mouse button was released. ' + 'x=' + str(event.x) + '   y=' + str(event.y))
        self.x = None
        self.y = None

    def right_mouse_down_motion_callback(self, event):
        self.root.status_bar.set('%s', 'Right mouse down motion. ' + 'x=' + str(event.x) + '   y=' + str(event.y))
        self.x = event.x
        self.y = event.y

    def left_mouse_click_callback(self, event):
        self.root.status_bar.set('%s', 'Left mouse button was clicked. ' + 'x=' + str(event.x) + '   y=' + str(
            event.y))
        self.x = event.x
        self.y = event.y

    # self.focus_set()
    def frame_resized_callback(self, event):
        print("frame resize callback")

    def create_graphic_objects(self):
        self.canvas.delete("all")
        r = np.random.rand()
        self.drawing_objects = []
        for scale in np.linspace(.1, 0.8, 20):
            self.drawing_objects.append(self.canvas.create_oval(int(scale * int(self.canvas.cget("width"))),
                                                                int(r * int(self.canvas.cget("height"))),
                                                                int((1 - scale) * int(self.canvas.cget("width"))),
                                                                int((1 - scale) * int(self.canvas.cget("height")))))

    def redisplay(self, event):
        self.create_graphic_objects()

    def matplotlib_plot_2d_callback(self):
        self.display_matplotlib_figure_on_tk_canvas()
        self.root.status_bar.set('%s', "called matplotlib_plot_2d_callback callback!")

    def matplotlib_plot_3d_callback(self):
        self.display_matplotlib_3d_figure_on_tk_canvas()
        self.root.status_bar.set('%s', "called matplotlib_plot_3d_callback callback!")

    def graphics_draw_callback(self):
        self.create_graphic_objects()
        self.root.status_bar.set('%s', "called the draw callback!")


def close_window_callback(root):
    if tk.messagebox.askokcancel("Quit", "Do you really wish to quit?"):
        root.destroy()


main_window = MainWindow(debug_print_flag=False)
# main_window.geometry("500x500")
main_window.wm_state('zoomed')
main_window.title('Assignment_05 --  Raina')
main_window.minsize(800, 600)
main_window.protocol("WM_DELETE_WINDOW", lambda root_window=main_window: close_window_callback(root_window))
main_window.mainloop()
