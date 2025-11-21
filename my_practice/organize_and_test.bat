@echo off
REM Chirag's Epic OOP Exam Prep Script
REM Because AI might take our jobs, but at least we can organize files before that happens! 🤖

echo ================================================================
echo    CHIRAG'S OOP SYLLABUS - VIBE CODER EDITION
echo    "Organizing code before AI replaces us all"
echo ================================================================
echo.

cd "c:\Users\Chirag\Desktop\sem 3\OOP\exam"

echo [1/6] Testing Unit 1 Files...
echo --------------------------------
javac -d "Unit1_Introduction" Unit1_OOP_Basics.java 2>nul && echo ✓ Unit1_OOP_Basics.java compiled || echo ✗ Unit1_OOP_Basics.java FAILED
javac -d "Unit1_Introduction" Unit1_ControlFlow.java 2>nul && echo ✓ Unit1_ControlFlow.java compiled || echo ✗ Unit1_ControlFlow.java FAILED
javac -d "Unit1_Introduction" Unit1_ClassesObjectsMethods.java 2>nul && echo ✓ Unit1_ClassesObjectsMethods.java compiled || echo ✗ Unit1_ClassesObjectsMethods.java FAILED
echo.

echo [2/6] Testing Unit 2 Files...
echo --------------------------------
javac -d "Unit2_Constructors" Unit2_Constructors.java 2>nul && echo ✓ Unit2_Constructors.java compiled || echo ✗ Unit2_Constructors.java FAILED
javac -d "Unit2_Constructors" Unit2_PackagesAccessComposition.java 2>nul && echo ✓ Unit2_PackagesAccessComposition.java compiled || echo ✗ Unit2_PackagesAccessComposition.java FAILED
javac -d "Unit2_Constructors" Unit2_GetterSetterKeywords.java 2>nul && echo ✓ Unit2_GetterSetterKeywords.java compiled || echo ✗ Unit2_GetterSetterKeywords.java FAILED
echo.

echo [3/6] Testing Unit 3 Files...
echo --------------------------------
javac -d "Unit3_AccessControl" Unit3_NestedClasses.java 2>nul && echo ✓ Unit3_NestedClasses.java compiled || echo ✗ Unit3_NestedClasses.java FAILED
javac -d "Unit3_AccessControl" Unit3_InterfacesAbstractClasses.java 2>nul && echo ✓ Unit3_InterfacesAbstractClasses.java compiled || echo ✗ Unit3_InterfacesAbstractClasses.java FAILED
echo.

echo [4/6] Testing Unit 4 Files...
echo --------------------------------
javac -d "Unit4_OOPConcepts" Unit4_InheritancePolymorphism.java 2>nul && echo ✓ Unit4_InheritancePolymorphism.java compiled || echo ✗ Unit4_InheritancePolymorphism.java FAILED
javac -d "Unit4_OOPConcepts" Unit4_ExceptionHandling.java 2>nul && echo ✓ Unit4_ExceptionHandling.java compiled || echo ✗ Unit4_ExceptionHandling.java FAILED
javac -d "Unit4_OOPConcepts" Unit4_FileIO.java 2>nul && echo ✓ Unit4_FileIO.java compiled || echo ✗ Unit4_FileIO.java FAILED
echo.

echo [5/6] Testing Unit 5 Files...
echo --------------------------------
javac -d "Unit5_Collections" Unit5_Collections.java 2>nul && echo ✓ Unit5_Collections.java compiled || echo ✗ Unit5_Collections.java FAILED
javac -d "Unit5_Collections" Unit5_IteratorsGenerics.java 2>nul && echo ✓ Unit5_IteratorsGenerics.java compiled || echo ✗ Unit5_IteratorsGenerics.java FAILED
echo.

echo [6/6] Testing Unit 6 Files...
echo --------------------------------
javac -d "Unit6_Multithreading" Unit6_Multithreading.java 2>nul && echo ✓ Unit6_Multithreading.java compiled || echo ✗ Unit6_Multithreading.java FAILED
echo.

echo ================================================================
echo Moving source files to respective folders...
echo ================================================================
move Unit1_*.java "Unit1_Introduction\" 2>nul
move Unit2_*.java "Unit2_Constructors\" 2>nul
move Unit3_*.java "Unit3_AccessControl\" 2>nul
move Unit4_*.java "Unit4_OOPConcepts\" 2>nul
move Unit5_*.java "Unit5_Collections\" 2>nul
move Unit6_*.java "Unit6_Multithreading\" 2>nul

echo.
echo ================================================================
echo    ALL DONE! Your OOP syllabus is organized, Chirag!
echo    Now go ace that exam before AI learns how to take tests too!
echo ================================================================
pause
