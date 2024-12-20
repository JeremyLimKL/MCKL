# JLKL
#--- This file is used to store functions that extract the specific data ---

def PriceFinder(Accepted_row):
    import csv
    filename = 'MY_details.v2.csv'
    with open(filename, 'r' , encoding = 'cp850') as csvfile:
        csv_reader = csv.reader(csvfile)
        list_of_rows = list(csv_reader)

        #↓ Stores value of prices for all the 'Accepted_row' into 'price_list'
        #   The '$' and 'per night' and ',' is removed to have integer value
    price_list = []
    for NumRow in Accepted_row:
        if list_of_rows[NumRow][7] == '':
            value = 0
        else:
            value = list_of_rows[NumRow][7]
            value = value.replace("$","")
            value = value.replace(" per night","")
            value = value.replace(",","")
        price_list.append(int(value))
    return (price_list)


def RatingFinder(Accepted_row):
    import csv
    filename = 'MY_details.v2.csv'
    with open(filename, 'r' , encoding = 'cp850') as csvfile:
        csv_reader = csv.reader(csvfile)
        list_of_rows = list(csv_reader)
        
        #↓ Stores values for ratings in 'rating_list' from the values of 'Accepted_row'
        #   The empty spaces('') have value of 0.0
    rating_list = []
    for NumRow in Accepted_row:
        if list_of_rows[NumRow][8]  == '':
            value = 0.0
        else:
            value = list_of_rows[NumRow][8]
        rating_list.append(float(value))
    return (rating_list)


def BedFinder(Accepted_row):
    import csv
    filename = 'MY_details.v2.csv'
    with open(filename, 'r' , encoding = 'cp850') as csvfile:
        csv_reader = csv.reader(csvfile)
        list_of_rows = list(csv_reader)

    #↓ Stores how many beds there are into 'bed_list' from values of 'Accepted_row'
    #   The empty spaces('') have value of 0 
    bed_list = []
    for NumRow in Accepted_row:
        if list_of_rows[NumRow][11]  == '':
            value = 0
        else:
            txt = list_of_rows[NumRow][11]      #The inputs are stored in 'txt'
            txt_list = txt.splitlines()         #The input is split in a list vy their multiple lines
            value_list = []
            for a in range (1,len(txt_list),2):     #This loop focus on choosing the odd index of the 'txt_list'
                for b in (txt_list[a]).split():     #This loop only accepts numbers from the given 'txt_list' index
                    try:                            #and would store them in 'value_list'
                        value_list.append(int(b))
                    except ValueError:
                        pass
            value = 0
            for i in value_list:                #This loop would sum every number of the 'value_list' into 'value'
                value = value + i
        bed_list.append(value)                  #The value is then stored in 'bed_list'
    return(bed_list)


def NumReviewFinder(Accepted_row):
    import csv
    filename = 'MY_details.v2.csv'
    with open(filename, 'r' , encoding = 'cp850') as csvfile:
        csv_reader = csv.reader(csvfile)
        list_of_rows = list(csv_reader)
        
    #↓ Stores how many reviews there are into 'numreview_list' from values of 'Accepted_row'
    #   The empty spaces('') have value of 0 
    numreview_list = []
    for NumRow in Accepted_row:
        if list_of_rows[NumRow][9]  == '':
            value = 0
        else:
            value = int(list_of_rows[NumRow][9])
        numreview_list.append(value)
    return(numreview_list)



def AmenitiesFinder(Accepted_row):
    import csv
    filename = 'MY_details.v2.csv'
    with open(filename, 'r' , encoding = 'cp850') as csvfile:
        csv_reader = csv.reader(csvfile)
        list_of_rows = list(csv_reader)

    #↓ Stores how many amenities there are into 'amenities list' from values of 'Accepted_row'
    #   The empty spaces('') have value of 0
    amenities_list = []
    for NumRow in Accepted_row:
        if list_of_rows[NumRow][10]  == '':
            value = 0
        else:
            txt = list_of_rows[NumRow][10]      #The input is stored in 'txt'
            txt = txt.splitlines()              #The input is split in a list by their multiple lines
            commandExit = False                 #This is used to prevent value(of amenities) from increasing when
            value = 0                           # "Unavailable" is found in a line
            for a in txt:
                for b in (a.split(" ")):
                    if b == "Unavailable:":
                        commandExit = True
                if commandExit == False:
                    value = value + 1
        amenities_list.append(value)
    return (amenities_list)



