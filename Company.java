/**
 * This class represents a rent company.
 * <BR>The rents in this company are sorted by the date receiving of the car.
 */
public class Company
{
    // declaring variables and constants
    private Rent[] _rents;
    private int _noOfRents;

    final int MAX_RENTS=1000;

    /**
     * Creates a new company object.
     */
    public Company (){
        _rents = new Rent [MAX_RENTS];
        _noOfRents = 0;
    }

    // this method moves the rent objects in the array rent 1 square ahead from n spot.
    private void moveRightFromIndexN (int n){
        for (int i= _noOfRents; i> n ; i--){
            _rents[i]= _rents[i-1];   
        }
    }

    /**
     * Adds a rent to the company and entering it to the array,
     * <BR>in a way that rentals will appear in the order of the date the car wad received.
     * 
     * @param name the name of the rent
     * @param car the car object in this rent
     * @param pick the date the rent started
     * @param ret the date the rent ended
     * 
     * @return true if the rent was added to the company
     */
    public boolean addRent (String name , Car car , Date pick , Date ret){
        if (_noOfRents== MAX_RENTS)
            return false;

        if (_noOfRents == 0){
            _rents[0]= new Rent (name, car, pick, ret);
            _noOfRents++;
            return true;
        }
        
        /* creating an index i which will represent the index of the rent which is before the new added rent,and will determine
           the index which the new rent will be located on the rents array (i+1)- acoording to the sort from early pickDate to late pickDate.*/
        int i; 
        for (i=_noOfRents-1 ; i>=0 ; i--){
            if (pick.after(_rents[i].getPickDate()) || pick.equals(_rents[i].getPickDate()))
                break;
        }
        
        if (i != _noOfRents-1 )// if the i index will be equal to the last rent, the added rent will now be the last rent after added.
            this.moveRightFromIndexN(i+1);
        
        _rents[i+1]= new Rent (name, car, pick, ret);// enter the added rent to the index i+1 in the rents array.
        _noOfRents++;
        
        return true;
    }

    /**
     * Removes the first rent that has returnDate d.
     * @return true if a rent with returnDate d was found and removed
     */
    public boolean removeRent(Date d){
        int i;
        for (i=0 ; i<_noOfRents ; i++){
            if(_rents[i].getReturnDate().equals(d)){
                for (int j=i ; j<_noOfRents-1 ; j++){
                    _rents[j]= _rents[j+1];
                }
                _rents [_noOfRents-1]= null;
                _noOfRents--;                
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the total price of all the rents in the company
     * @return sum of the rents prices
     */
    public int getSumOfPrices (){
        int sum=0;
        for (int i=0 ; i<_noOfRents ; i++){
            sum += _rents[i].getPrice();
        }
        return sum;
    }

    /**
     * Returns the total days of all the rents in the company
     * @return sum of the days in rent
     */
    public int getSumOfDays(){
        int sum=0;
        for(int i=0 ; i<_noOfRents ; i++){
            sum += _rents [i].howManyDays();
        }
        return sum;
    }

    /**
     * Returns the avarage days for the rents in the company
     * @return average days of the rents
     */
    public double averageRent(){
        if(_noOfRents==0)
            return 0;
        return getSumOfDays()/(double)_noOfRents;
    }

    /**
     * Returns the car that has the latest returnDate
     * @return the car that has the latest returnDate
     */
    public Car lastCarRent(){
        if (_noOfRents==0)
            return null;

        Rent lastRent= new Rent (_rents[0]);
        for (int i=1 ; i<_noOfRents ; i++){
            if ((_rents[i].getReturnDate()).after(lastRent.getReturnDate())){
                lastRent=new Rent (_rents[i]);
            }
        }
        return new Car (lastRent.getCar());
    }

    /**
     * Returns the longest rent.
     * @return the longest rent.
     */
    public Rent longestRent(){
        if(_noOfRents==0)
            return null;

        Rent longestRent= new Rent (_rents[0]);
        for (int i =1 ; i< _noOfRents ; i++){
            if ( _rents[i].howManyDays() > longestRent.howManyDays() ){
                longestRent= new Rent (_rents[i]);
            }
        }
        return longestRent;
    }

    /**
     *  Returns the most common car type among all rents
     *  @returns the most common car type among all rents
     */
    public char mostCommonRate(){
        if (_noOfRents==0)
            return 'N';

        char ch;
        int [] ratesArray = new int [4];// creating an array representing the number of rates from each type.
        for (int i=0; i<_noOfRents; i++){
            ch = _rents[i].getCar().getType();
            ratesArray [ch-'A'] ++;
        }
        
        char mostCommon= 'A';
        int highestRateInArray= ratesArray[0] ;
        for (int i=1; i<ratesArray.length; i++){
            if (ratesArray [i] >= highestRateInArray){
                highestRateInArray = ratesArray[i];
                mostCommon = (char)(i + (int)'A');
            }
        }
        
        return mostCommon;
    }

    /**
     * Returns a String that represents the rents in this company
     * @return String that represents this company in the following format:
     * <BR>The company has 3 rents:
     * <BR>Name:Ruthi From:10/03/2022 To:14/03/2022 Type:A Days:4 Price:400
     * <BR>Name:Lior From:11/03/2022 To:18/03/2022 Type:C Days:7 Price:1134
     * <BR>Name:Rama From:30/10/2022 To:12/11/2022 Type:B Days:13 Price:1845
     * 
     * <BR>if the company has no rents, the method will return the following string:
     * <BR>The company has 0 rents.
     */
    public String toString(){
        if (_noOfRents==0)
            return "The company has 0 rents.";
        String str= "The company has " + _noOfRents + " rents:";
        for (int i =0 ; i< _noOfRents ; i++){
            str= str + "\n" + _rents[i].toString();
        }
        return str;
    }
}