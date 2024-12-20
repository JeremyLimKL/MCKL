# JLKL
import csv
filename = 'MY_details.v2.csv'

with open(filename, 'r' , encoding = 'cp850') as csvfile:
    csv_reader = csv.reader(csvfile)
    list_of_rows = list(csv_reader)
#^Reads the csv and contains the 2d List in 'list_of_rows'

num = 0
Accepted_row = []
location = str(input("National or KL level? (Nation or KL): "))     #To see if users want to find National or KL values
if location == 'KL':
    #↓ Search location column for 'kuala lumpur' and 'wilayah persekutuan kuala lumpur'
    #   and stores the accepted row number as 'Accepted_row'
    for NumRow in range(len(list_of_rows)):
        xLocation = list_of_rows[NumRow][2]                     #Row value is extracted
        xLocation = xLocation.split(", ")                       #The values are seperated into list
        commandExit = False                     #Used to ensure no repeation of accepting row number
        for txt in xLocation:
            if "kuala lumpur"==txt.lower() and commandExit==False:
                Accepted_row.append(NumRow)     #If location is in one of the index, the row number is stored
                commandExit = True              #  in a new list name 'Accepted_row'
                num += 1
            elif "wilayah persekutuan kuala lumpur"==txt.lower() and commandExit==False:
                Accepted_row.append(NumRow)
                commandExit = True
                num += 1
    print ('Number of KL locations found',num)      #Prints how many accepted values are there
else:
    for NumRow in range(len(list_of_rows)):
        if NumRow != 0:                     #Avoids title
            Accepted_row.append(NumRow)     #Places all values as 'Accepted_row' for National coverage
            num += 1
    print ('Number of National locations found',num)#Prints how many accepted values are there

import ProjectFunctions     #Import the functions which are then stored
p = ProjectFunctions.PriceFinder(Accepted_row)
r = ProjectFunctions.RatingFinder(Accepted_row)
b = ProjectFunctions.BedFinder(Accepted_row)
nr = ProjectFunctions.NumReviewFinder(Accepted_row)
a = ProjectFunctions.AmenitiesFinder(Accepted_row)
price=[]
rating=[]
bed = []
numberofreview = []
amenities = []
num = 0
for n in range(len(Accepted_row)):                #Its used to go through all index values in Accepted_row 
    if p[n]==0 or r[n]==0 or b[n] == 0 or nr[n]==0 or a[n]==0:          #Used to get rid of values of 0s
        pass
    else:
        price.append(p[n])                      #New filtered index values are stored in new variable list
        rating.append(r[n])
        bed.append(b[n])
        numberofreview.append(nr[n])
        amenities.append(a[n])
        num += 1
print('Number of rows left after removing 0s:',num)     #Prints how many new filtered values are there

def Pearson_correlation(data1,data2):           #function is used to find the correlation between the two list
    import numpy as np
    X = np.array( data1 )
    Y = np.array( data2 )
    if len(X)==len(Y):
        Sum_xy = sum((X-X.mean())*(Y-Y.mean()))
        Sum_x_squared = sum((X-X.mean())**2)
        Sum_y_squared = sum((Y-Y.mean())**2)
        corr = Sum_xy / np.sqrt(Sum_x_squared * Sum_y_squared)
    return corr

def datagrapher(request1,request2,data1,data2):    #Produces the Graph out             
    print("Correlation:",Pearson_correlation(data1,data2))  #Prints the correlation
    import numpy as np
    import pandas as pd
    from matplotlib import pyplot as plt
    x = pd.Series(data1)
    y = pd.Series(data2)
    plt.scatter(x,y)            #Plots the list values into a graph
    plt.xlabel(request1)        #Writes the labels of the graph
    plt.ylabel(request2)
    #↓ Produces the colleration Red line in graph
    plt.plot(np.unique(x) , np.poly1d(np.polyfit(x,y,1))(np.unique(x)),color='red')
    plt.show()

numbername_dict = {0:'Price',1:'Rating',2:'Bed',3:'NumberOfReview',4:'Amenities'}
numberfile_dict = {0:price,1:rating,2:bed,3:numberofreview,4:amenities}
source_dict={'Price':price,'Rating':rating,'Bed':bed,'NumberOfReview':numberofreview,'Amenities':amenities}
print('-------------------\n')
for a in range(2):              #Used to print out all correlation value between prices and ratings with others
    for b in range(5):
        if numbername_dict[a]==numbername_dict[b]:      #Avoids the collerating the same values
            pass
        else:
            print(numbername_dict[a],'against',numbername_dict[b],end=': ')
            print(Pearson_correlation(numberfile_dict[a],numberfile_dict[b]),'\n')
    print('-------------------\n')       


print("Graph generator")            #Request which values the user wants to compare and produce a graph
request1 = str(input("Data1 (Price,Rating,Bed,NumberOfReview,Amenities): "))
data1 = source_dict.get(request1)
request2 = str(input("Data2 (Price,Rating,Bed,NumberOfReview,Amenities): "))
data2 = source_dict.get(request2)
datagrapher(request1,request2,data1,data2)











