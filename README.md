# GestionaleBeta
Test interface per Gestionale pizzeria con Ferri Luca.

Written in java, updates coming soon with implementation of Jbutton ActionListeneers.

Created Database Local with MySql and saved Users as utente
with relative attributes like adress, name, phone_number...

The main database to use now is the Final.db,
configured as same as test.db, now is the main db.

# Three Tbas in GestionaPizza App:

We have three tabs added in the JTabbedPane:
* DisponibilitaOrari:
  * First we have a JLabel on the north
  * We Displays time buttons, in the center of the frame,
  from 18 to 22 and by pressing one you should choose it as
  your time for your order; it is automatically saved in the
  Order dB with curreent date
  * Then we have two buttons glued horrizontally at the end 
  of the Frame different depending on the tab we are in.
* Indirizzo:
  * we have on the north a serchBar connected to the table 
  utente in th db which is allocated dynamically and a cutomer
  is added everytime it is used as an order with.
  * Underneath this Serach Bar we get the result based on what
  you write with the same phone number or name depending on inout
  and an Adress should be selcted to continue;
  if selected it should directly take you to Pizza_Tab //ToImplement
  * At the end of the frame we have PrevNewxtButtone glued horizontally
  but if triggered without selection they should generate a warning.
* Pizza:
  * Here the Frame is divided in 4 parts with GridLayout //Look how to make
  hgap and vgap automatic according to the frame_size:
    * Top Left we have a JTable thats sums up all the pizza in the current
    order with realtive time a pizza is choosen and the ingredients added to it.
    * Top Right we have most common pizza that add directly a pizza to the order
    if already present it adds the specific pizza counter in RiepilogoiOrdine
    //This part should be implemented and also the actions to do so with the 
    order table of the database.
    * Bottom Left we have a sum of the users details like name and adress and 
    phone_number.
    * Bottom Right we have the possibilities to add variants with a check list that
    adds the variant as first ingredient and ingredients with the +
    button can be added.They will appear also on the Ingredient Column
    //To configure maybe put variants in a different color in >Ingredient Column.

*Then we have two buttons glued horrizontally at the end
of the Frame different depending on the tab we are in.

# Database details:

Implementing now the actionperformed on the time buttons and PrevNextButtons,
so that when pressed they save the time in the Ordine table
of the dataBase and then move you directly to the adress_Tab where you selct the addrss
or add it.
If you choose an adress or if avanti is selected it moves you to Pizza_Tab
where you can create pizza; if no address is selected and avanti is pressed a
Jdialog should popup asking you if you wish to continue even if no adress is choosen
or name //Tofinalize add tag.

Last tab is Pizza where you can create and add pizzas to the order in ordine table
of db //Tofinalize add tag.

# PrevNextButton Class

There is the Order button in first tab that shows you
all of todays order
Then int this tab we also have avanti button.

IndirizzoTab has prevTab button and
Fine_button in that saves the order and adds 
its tuple or whatever to the table of ordine.
Then there shoul be an update on the buttons color after 
which it shows you the saved order //Tofinalize add tag.
