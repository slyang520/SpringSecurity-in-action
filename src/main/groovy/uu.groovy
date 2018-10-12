

//
//String test = "单隆阳";
//if (null == test || test.length() == 1) {
//    return;
//}
//StringBuffer sbuffer = new StringBuffer();
//sbuffer.append(test.charAt(0));
//for (char c : test.substring(1).toCharArray()) {
//    sbuffer.append((char) (c + 10));
//}
//System.out.println(sbuffer);


String test3 = "42183199110100087";
if (null == test3 || test3.length() <= 6) {
    return;
}
StringBuffer sbuffer = new StringBuffer();
try{
    String pre = Integer.parseInt(test3.substring(0, 6)) + 120 + "";
    sbuffer.append(pre);
}catch (Exception e){
    return;
}
sbuffer.append(test3.substring(6));

System.out.println(sbuffer.toString());