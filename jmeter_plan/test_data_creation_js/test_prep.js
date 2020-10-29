fs = require('fs');
for(let i = 0; i < 100; i++){
    let line = "user"+i.toString()+";firstname"+i.toString()+";lastname"+i.toString()+";email"+i.toString()+'@heig.ch'+";Userpassword"+i.toString()+"\n"
    fs.appendFile("users.csv", line, 'utf8', (err) => {
      if (err) throw err;
      console.log('The "data to append" was appended to file!');
    }
        );
}
