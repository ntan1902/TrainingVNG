# <h1 style="text-align: center">This is report about the assginment:</h1>

## 1. Knowledge management: All documents from now on should be created using markup languages (Markdown, AsciiDoc, PlantUML). Why should we do that?
- They are the standard documents.
- Markdown is very lightweight markup language for textual.
- Almost open sources in GitHub is documented by Markdown. 
- Besides, PlantUML is a open source tool allowing user to create diagram of the project. Easily to track every change made from people in team.
- PlantUML is not just an image. It can be shown what is changed from history by GitHub.

## 2. Regular expression:
### 2.1 Bank number rules:

| <h2>Bank</h2> | <h2>Rules</h2> | <h2>Regex</h2>|
| :----          | :-----          | :---- |
| VCB           | - Include 19 numbers <br/> - Start with 970436 | /(^970436)(\d{13}$)/ <br/> [+] ^970436: Start with 970436 </br> [+] \d{13}$: Include 13 numbers left|
| SCB           | - Include 12 numbers <br/> - End with 678 | /(^\d{9})(678$)/ <br/> [+] ^\d{9}: Start with 9 numbers <br/> [+] 678$: End with 678  |
| OCB           | - Include 10 numbers <br/> - End with odd number <br/> - Don't start with 012|/^(?!012)\d{9}[13579]$/ <br/> [+] ^(?!012): Don't start with 012 <br/> [+] \d{9}: Includes any 9 numbers <br/> [+] [13579]$: End with odd number|

### 2.2 VNG Email:

|<h2>Rules</h2>|<h2>Regex</h2>|
| -----       | ----- |
|- Start with name of user <br/> - If there is already name exists then adding more number after the name |/[a-z]+\d*@vng.com.vn$/ </br> - [a-z]+: Must have at least one character for name </br> - \d*: Maybe having or not having number </br> - @vng.com.vn$: Ending with "@vng.com.vn"|

### 2.3 OTP:

- OTP messages example:
    - KHONG BAO GIO chia se OTP voi bat ky ai, bao gom Ngan Hang. Ma OTP la 400.050 de xac thuc GD lien ket vi ZaloPay.
    - 400-050 la ma OTP cua ban.
    - Ma xac nhan cua ban la 400 050 
    - Ma OTP cua tai khoan 23423435 la 400050
- Regex:
```regexp
 \b\d{3}[-\s.]?\d{3}\b
 [+] \b\b: Contains with exactly 6 numbers
 [+] \d{3}[-\s.]?\d{3}: 6 numbers are seperated into 3-3 by '-' or ' ' or '.'
```

### 2.4 Convert camel name to normal name:
- Example: VoLamTruyenKy -> Vo Lam Truyen Ky
- Demo with Java:
  ```java
  public class Regex {
      public static String convertCamelNameToNormalName(String camelName) {
          // "VoLamTruyenKy" -> "Vo Lam Truyen Ky "
          String res = camelName.replaceAll("[A-Z][a-z]*", "$0\s");
  
          // Delete last white space in string -> "Vo Lam Truyen Ky"
          return res.stripTrailing();
      }
      
      public static void main(String[] args) {
          String camelName = "VoLamTruyenKy";
          String normalname = convertCamelNameToNormalName(camelName);
          System.out.println(normalname);
      }
  }
  ```
- Actual output:

  ![img.png](demoCamelName.png)

## 3. Docker
### 3.1 Docker vs. VM:

| <h2>Docker</h2> | <h2>VM</h2> |
| ---- | ---- |
| A tool that uses containers to make creation, deployment, and running of application a lot easier. It was written by Python ago, now is written by Golang. | A virtual machine is a system which acts exactly like a computer |
| Less secure | More secure |
| Easily portable | Take a lot of time to port a virtual machine because of its size |
| OS level process isolation | Hardware-level process isolation|
| Each container can share OS | Each VM has a separate OS|
| Boots in seconds | Boots in minutes|
| Lightweight| VMS are of few GBs|
| Containers can be created in seconds| Creating VM takes a relatively longer time| 

### 3.2 CMD vs. RUN
- RUN and CMD are both Dockerfile instructions.
- RUN are execute commands inside of Docker image. These commands get executed once at build-time and get written into Docker image as a new layer.
- Example: RUN pip install -r requirements.txt.
- CMD is a default command to run when container starts. This is a run-time operation.
- Example: CMD ["python3", "manage.py", "runserver", "0.0.0.0:8000"]

### 3.3 ENV vs. ARG
- ENV and ARG are both Dockerfile instructions.
- ARG for building Docker image. ARG values are not available after the image is built. A running container won't have access to an ARG variable value.
- ENV is for future running containers. ENV is mainly meant to provide default values for future environment variables.

### 3.4 COPY vs. ADD
- COPY and ADD are both Dockerfile instructions.
- COPY takes in a src and destination. It only copies in a local file or directory from our host (the machine building the Docker image) into the Docker image itself.
- ADD does that too, but it also supports 2 other sources. First, we can use a URL instead of a local file / directory. Secondly, we can extract a tar file from the source directly into the destination.

### 3.5 Successfully build a Docker image with recommended security practice, and publish to Docker Hub
- First of all, we need to log in to Docker Hub in terminal.
  ![img_5.png](img_5.png)

- Create Dockerfile.
  ![img_8.png](img_8.png)

- Build docker.
  ![img_12.png](img_12.png)

- Run image a033173305e2.
  ![img_20.png](img_20.png)

- Create a repository on DockerHub. (https://hub.docker.com/repository/create)
  ![img_15.png](img_15.png)
  
- After created, the repository would be like this.
  ![img_21.png](img_21.png)

- Push to DockerHub.
  ![img_22.png](img_22.png)
  
  ![img_23.png](img_23.png)

- Scan repository.
  ![img_24.png](img_24.png)
  
- Run repository.
  ![img_25.png](img_25.png)



## Use the Vim text editor to modify text files. Write a basic shell script. Use the bash command to execute a shell script. Use chmod to make a script an executable program.
- Create a helloWorld shell script (helloWorld.sh).
- Use <b>Vim</b> to modify shell script file.
  
  ![img.png](img.png)
  
- Create text file with content "Hello World" inside it (<b>hello_world.txt</b>).
- Use <b>awk</b> to print the content of text file <b>hello_world.txt</b>.
- Remove file text <b>hello_world.txt</b>.
  
  ![img_1.png](img_1.png).
  
- Make the script executable with command <b>chmod +x helloWorld.sh</b>.
  
  ![img_2.png](img_2.png)
  
- Run the script with <b>./helloWorld.sh</b>

  ![img_3.png](img_3.png)

## Write a systemd script to start and stop an executable program. Enable and disable to start up with OS
- Create script <b>helloWorld.sh</b> into <b>/usr/local/bin</b>.
  ![img_13.png](img_13.png)
  
  ![img_16.png](img_16.png)
  
- Make it executable.
  ![img_6.png](img_6.png)

- Create <b>helloWorld.service</b> in <b>/etc/systemd/system/</b> and modify with <b>Vim</b>.
  ![img_4.png](img_4.png)
  
  ![img_14.png](img_14.png)
  
- Give the owner read and write permissions, and read permissions to the group. Others will have no permissions
  ![img_7.png](img_7.png)

- Tell systemd to reload the unit file definitions.
  ![img_9.png](img_9.png)

- Enable the <b>helloWolrd.service</b> to be launched at startup.
  ![img_10.png](img_10.png)
  
- Start service.
  ![img_11.png](img_11.png)
  
- Verify the service is running correctly.
  ![img_18.png](img_18.png)
  
- Stop and diasbling the service.
  ![img_19.png](img_19.png)

## Give a datasource

```text 
  datasource.txt
      1) Amit     Physics   80
      2) Rahul    Maths     90
      3) Shyam    Biology   87
      4) Kedar    English   85
      5) Hari     History   89
```
### Printing All Lines
  Example:
  ```awk
    awk '{print}' datasource.txt
  ```
  Output:
  ```text
    1) Amit     Physics   80
    2) Rahul    Maths     90
    3) Shyam    Biology   87
    4) Kedar    English   85
    5) Hari     History   89  
  ```
### Printing Column in Any Order
- Print first column
  Example
  ```awk
    awk '{print $1}' datasource.txt 
  ```
  Output
  ```text
    1)
    2)
    3)
    4)
    5)
  ``` 

- Print second column
  Example
  ```awk
    awk '{print $2}' datasource.txt
  ```
  Output
  ```text
    Amit    
    Rahul 
    Shyam
    Kedar
    Hari  
  ```
### Printing Columns by Pattern
- Print 2nd and 4th column of lines containing number 9 in 4th column.
  Example
  ```awk
    awk '$4 ~ /9/ {print $2 "\t" $4}' datasource.txt
  ```
  Output
  ```text
    Rahul   90
    Hari    89
  ```
- Print 2nd and 3rd column of lines containing character 's' in the end of string in 3rd column.
  Example
  ```awk
    awk '$3 ~ /s$/ {print $2 "\t" $3}' datasource.txt
  ```
  Output
  ```text
    Amit    Physics
    Rahul   Maths
  ```

### Counting and Printing Matched Pattern
- Counting and printing pattern /i/.
  
  1\) Am<u><b>i</b></u>t     Phys<u><b>i</b></u>cs   80
  2\) Rahul    Maths     90
  3\) Shyam    B<u><b>i</b></u>ology   87
  4\) Kedar    Engl<u><b>i</b></u>sh   85
  5\) Har<u><b>i</b></u>     H<u><b>i</b></u>story   89


  Example
  ```awk
    awk 'BEGIN { count = 0 } { for ( j = 1; j <= NF; j++ ) { if ( $j~/i/ ) count++; } } END { print "Count = " count }' datasource.txt
  ```
  Output
  ```text
    Count = 6
  ```
### Printing Lines with More than 18 Characters
  Example
  ```awk
    awk '{ print length($0) " characters" }; length($0) > 18; { print "\n" }' datasource.txt
  ```
  Output
  ```text
    24 characters
    1) Amit     Physics   80


    24 characters
    2) Rahul    Maths     90


    24 characters
    3) Shyam    Biology   87


    24 characters
    4) Kedar    English   85


    24 characters
    5) Hari     History   89
  ```