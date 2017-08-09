#!/bin/bash
####################################
#
# Backup DATABASE
#
####################################

dest="/nvqs-container/database-backup"
filename="nghiavuquansu"_`date +%Y-%m-%d_%H:%M:%S`".sql"
databasename=nghiavuquansu
username="root"
password="root"

dumDB(){
  echo "Stating backup DB NghiaVuQuanSu"
  mysqldump -u $username -p$password $databasename > $dest"/"$filename
  echo "Finish"
}

main(){
  dumDB
}

main

exit 0




